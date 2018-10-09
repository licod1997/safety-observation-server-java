package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.dto.FeedbackDTO;
import vn.edu.fpt.service.FeedbackService;

import java.io.IOException;
import java.util.Date;


@RestController
public class FeedbackControler {


    @Autowired
    private FeedbackService feedbackService;

    @PostMapping( value = "/sendFeedback"
            , consumes = "application/json",
            produces = "application/json"
    )

    public String sendFeedback( @RequestBody FeedbackDTO feedbackDTO ) {
        Date a = feedbackDTO.getTime();
        long time = a.getTime();


        return (feedbackService.sendFeedback( feedbackDTO.getFeedbackDescription(), feedbackDTO.getFeedbackPhotoList(), time ));
    }

    @PostMapping( value = "/uploadImage" )

    public String uploadImage( @RequestPart( name = "img" ) MultipartFile img ) throws IOException {
        return feedbackService.uploadImage( img );
    }

    @GetMapping( {"/", "phan-hoi"} )
    public ModelAndView getFeedbackPage(ModelAndView mv) {
        mv.setViewName( "phan-hoi" );
        return mv;
    }
}
