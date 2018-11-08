package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import vn.edu.fpt.dto.FeedbackDTO;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.entity.FeedbackPhoto;
import vn.edu.fpt.payload.UploadFileResponse;
import vn.edu.fpt.service.FeedbackService;
import vn.edu.fpt.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

    @GetMapping( {"/chi-tiet-phan-hoi"}   )
    public ModelAndView getFeedbackPage( @RequestParam(name = "id") Long id, ModelAndView mv ) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        mv.addObject( "feedback", feedback );
               mv.setViewName( "chi-tiet-phan-hoi" );
        return mv;
    }

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType( MediaType.parseMediaType(contentType))
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



    @GetMapping( "/set-reject-photo" )
    public RedirectView setPhotoReject( @RequestParam( name = "photoId" ) Long photoId, @RequestParam (name = "option") boolean option ) {
        FeedbackPhoto feedbackPhoto = feedbackService.setPhotoReject( photoId, option );
        if ( feedbackPhoto != null ) {
//            return "set_reject_successfully";
            return new RedirectView( "/chi-tiet-phan-hoi?id=" + feedbackPhoto.getFeedback().getId() );
        }
        return new RedirectView( "/chi-tiet-phan-hoi?id=" + feedbackPhoto.getFeedback().getId() );

    }
//    @PostMapping( "/reject-feedback" )
//    public ResponseEntity setFeedbackReject( @RequestParam( name = "feedbackId" ) Long feedbackId ) {
//        Feedback feedback = feedbackService.setFeedbackReject( feedbackId );
//        if ( feedback != null ) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }

}
