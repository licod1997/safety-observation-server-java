package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.dto.DemoDTO;
import vn.edu.fpt.dto.NotificationDTO;
import vn.edu.fpt.service.DemoService;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping( value = "/login",
            consumes = "application/json",
            produces = "application/json" )
    public ResponseEntity login( @RequestBody DemoDTO demoDTO ) {
        return ResponseEntity.ok( demoService.login(demoDTO.getUsername(), demoDTO.getPassword()) );
    }


}
