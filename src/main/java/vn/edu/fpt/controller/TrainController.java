package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.edu.fpt.entity.TrainFile;
import vn.edu.fpt.payload.UploadFileResponse;
import vn.edu.fpt.service.FileStorageService;
import vn.edu.fpt.service.TrainService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TrainController {
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    TrainService trainService;

    @GetMapping( {"/train" } )
    public ModelAndView getTrainPage( ModelAndView mv ) {
        List<TrainFile> fileNotTrainYetList = trainService.getAllFileNotTrainYet();
        if(fileNotTrainYetList.size() > 0 ){
            System.out.println( fileNotTrainYetList );
            mv.addObject( "fileNotTrainYetList", fileNotTrainYetList );
            mv.addObject( "firstId", fileNotTrainYetList.get( 0 ).getId() );
            mv.addObject( "lastId", fileNotTrainYetList.get( fileNotTrainYetList.size() - 1 ).getId() );
            mv.setViewName( "train" );
        }

        return mv;
    }

    @PostMapping("/uploadMultipleTrainFiles")
    public List<UploadFileResponse> uploadMultipleTrainFiles( @RequestParam("files") MultipartFile[] files) {
        List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect( Collectors.toList());

        for ( MultipartFile file: files) {
            TrainFile trainFile = new TrainFile();
            trainFile.setFileDirectory(fileStorageService.getFileStorageLocation().toString() );
            trainFile.setFileName( file.getOriginalFilename() );
            trainService.addFile( trainFile );
        }
        return list;
    }
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}