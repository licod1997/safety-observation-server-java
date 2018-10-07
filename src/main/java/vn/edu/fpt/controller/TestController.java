package vn.edu.fpt.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.dto.DetectionResultDTO;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @PostMapping( "/upload" )
    public List<DetectionResultDTO> uploadFile( @RequestParam( "file" ) String fileBase64 ) throws Exception {

        // Normalize file name
        byte[] imageByte = Base64.decodeBase64( fileBase64 );

        String workingDir = System.getProperty("user.dir");

        new FileOutputStream( workingDir + "/img.jpg" ).write( imageByte );

        List<DetectionResultDTO> list = new ArrayList<>();
        list.add( new DetectionResultDTO( "Dangerous Object", 0.6 ) );
        list.add( new DetectionResultDTO( "Dangerous Object", 0.7 ) );
        list.add( new DetectionResultDTO( "Dangerous Object", 0.8 ) );
        return list;
    }
}
