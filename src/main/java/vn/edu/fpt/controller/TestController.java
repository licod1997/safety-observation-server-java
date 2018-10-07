package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.edu.fpt.dto.DetectionResultDTO;
import vn.edu.fpt.service.FileStorageService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping( "/upload" )
    public List<DetectionResultDTO> uploadFile( @RequestParam( "file" ) MultipartFile file ) {
        String fileName = fileStorageService.storeFile( file );

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path( "/downloadFile/" )
                .path( fileName )
                .toUriString();
        List<DetectionResultDTO> list = new ArrayList<>();
        list.add( new DetectionResultDTO("Dangerous Object", 0.6) );
        list.add( new DetectionResultDTO("Dangerous Object", 0.7) );
        list.add( new DetectionResultDTO("Dangerous Object", 0.8) );
        return list;
    }
}
