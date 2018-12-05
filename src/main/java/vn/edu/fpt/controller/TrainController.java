package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.edu.fpt.configuration.security.SecurityUser;
import vn.edu.fpt.entity.TrainingFile;
import vn.edu.fpt.payload.UploadFileResponse;
import vn.edu.fpt.service.FileStorageService;
import vn.edu.fpt.service.TrainService;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class TrainController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private FileStorageService fileStorageService;
    private Thread thread;

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @GetMapping( {"/tap-tin-huan-luyen"} )
    public ModelAndView getTrainPage( ModelAndView mv,
                                      Authentication auth ) {
        if ( auth != null ) {
            SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
            mv.addObject( "loggedInUser", securityUser.getUsername() );
        }
        List<TrainingFile> fileNotTrainYetList = trainService.getAllFileNotTrainYet();
        if ( fileNotTrainYetList.size() > 0 ) {
            System.out.println( fileNotTrainYetList );
            mv.addObject( "fileNotTrainYetList", fileNotTrainYetList );
            mv.addObject( "firstId", fileNotTrainYetList.get( 0 ).getId() );
            mv.addObject( "lastId", fileNotTrainYetList.get( fileNotTrainYetList.size() - 1 ).getId() );
            mv.setViewName( "tap-tin-huan-luyen" );
        }

        return mv;
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @PostMapping( "/tai-len-nhieu-tap-tin-xml" )
    public List<UploadFileResponse> uploadMultipleTrainFiles( @RequestParam( "files" ) MultipartFile[] files ) {
        //add them param User_ID khi đã có login
        List<UploadFileResponse> list = Arrays.asList( files )
                .stream()
                .map( file -> uploadFile( file ) )
                .collect( Collectors.toList() );
        Date time;
        for ( MultipartFile file : files ) {
            time = Calendar.getInstance().getTime();
            TrainingFile trainingFile = new TrainingFile();
            trainingFile.setFileDirectory( fileStorageService.getFileStorageLocation().toString() );
            trainingFile.setFileName( file.getOriginalFilename() );
            trainingFile.setTimeUpload( time );
            //set thêm user vào khi có chức năng login

            trainService.addFile( trainingFile );

        }
        return list;
    }

    public UploadFileResponse uploadFile( @RequestParam( "file" ) MultipartFile file ) {
        String fileName = fileStorageService.storeFile( file );

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path( "/downloadFile/" )
                .path( fileName )
                .toUriString();

        return new UploadFileResponse( fileName, fileDownloadUri,
                file.getContentType(), file.getSize() );
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @GetMapping( "/bat-dau-huan-luyen" )
    public ResponseEntity startTraining() {
        if ( thread == null ) {
            thread = new Thread( trainService );
        }
        if ( thread != null && !thread.isAlive() ) {
            trainService.preprocessBeforeTraining();
            thread.start();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @GetMapping( "/kiem-tra-tien-trinh-huan-luyen" )
    public ResponseEntity checkTrainingThread() {
        if ( thread != null && thread.isAlive() ) {
            File folder = new File( "C:\\Users\\Notebook\\Desktop\\train_model\\models\\research\\object_detection\\train" );
            File[] listOfFiles = folder.listFiles();

            int lastestFile = 0;
            int percentage = 0;
            List<Integer> listFile = new ArrayList<>();
            Pattern pattern = Pattern.compile( "model.ckpt-(\\d+).index" );
            for ( int i = 0; i < listOfFiles.length; i++ ) {
                if ( listOfFiles[i].isFile() ) {
                    Matcher matcher = pattern.matcher( listOfFiles[i].getName() );
                    if ( matcher.find() ) {
                        listFile.add( Integer.parseInt( matcher.group( 1 ) ) );
                    }
                }
            }
            if (listFile.size() > 0) {
                lastestFile = listFile.get( listFile.size() - 1 );
            }
            percentage = (lastestFile / 50000) * 100;
            return ResponseEntity.ok().body( percentage );
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @GetMapping( "/dung-huan-luyen" )
    public ResponseEntity stopTraining() {
        trainService.stopProcessBuilder();
        return ResponseEntity.ok().build();
    }
}