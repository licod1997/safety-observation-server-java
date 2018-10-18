package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.dto.FeedbackDTO;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.service.FeedbackService;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
public class FeedbackController {


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
    public ModelAndView getFeedbackPage( ModelAndView mv ) {
        List<Feedback> feedbackList = feedbackService.getFeedbacksLoadPage();
        System.out.println( feedbackList );
        mv.addObject( "feedbackList", feedbackList );
        mv.addObject( "firstId", feedbackList.get( 0 ).getId() );
        mv.addObject( "lastId", feedbackList.get( feedbackList.size() - 1 ).getId() );
        mv.setViewName( "phan-hoi" );
        return mv;
    }

    @PostMapping( "/phan-hoi-moi" )
    public ModelAndView getFeedbackFirstPage( @RequestParam( name = "firstNotificationId" ) Long id,
                                              ModelAndView mv ) {
        List<Feedback> feedbackList = feedbackService.getFeedbacksFirstPage( id );
        if ( feedbackList.size() > 0 ) {
            mv.addObject( "feedbackList", feedbackList );
        }
        mv.setViewName( "phan-hoi :: feedbackTable" );
        return mv;
    }

    @PostMapping( "/phan-hoi-cu" )
    public ModelAndView getFeedbackLastPage( @RequestParam( name = "lastNotificationId" ) Long id,
                                             ModelAndView mv ) {
        List<Feedback> feedbackList = feedbackService.getFeedbacksLastPage( id );
        if ( feedbackList.size() > 0 ) {
            mv.addObject( "feedbackList", feedbackList );
        }
        mv.setViewName( "phan-hoi :: feedbackTable" );
        return mv;
    }

    @PostMapping( "/da-xem-phan-hoi" )
    public ResponseEntity setFeedbackRead( @RequestParam( name = "currentNotificationId" ) Long id ) {
        Feedback feedback = feedbackService.setFeedbackRead( id );
        if ( feedback != null ) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
