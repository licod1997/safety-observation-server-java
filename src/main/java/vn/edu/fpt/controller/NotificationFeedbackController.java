package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.dto.NotificationFeedbackDTO;
import vn.edu.fpt.dto.NotificationMobileDTO;
import vn.edu.fpt.entity.NotificationFeedback;
import vn.edu.fpt.service.NotificationFeedbackService;

@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
public class NotificationFeedbackController {
    @Autowired
    private NotificationFeedbackService notificationFeedbackService;

    @PreAuthorize( "hasRole('ROLE_USER')" )
    @PostMapping( "/create-notification-feedback" )
    public ResponseEntity createNotificationFeedback( @RequestBody NotificationFeedbackDTO notificationFeedbackDTO ) {
        String description = notificationFeedbackDTO.getDescription();
        String imageURL = notificationFeedbackDTO.getImageURL();
        Long notiID = notificationFeedbackDTO.getNotificationId();
        System.out.println(notiID);
        NotificationFeedback notificationFeedback = notificationFeedbackService.createNotificationFeedback( description,notiID, imageURL );
        if ( notificationFeedback != null ) {
            notificationFeedbackDTO.setId( notificationFeedback.getId() );
            notificationFeedbackDTO.setDescription( notificationFeedback.getDescription() );
            notificationFeedbackDTO.setImageURL( notificationFeedback.getImageURL() );
            notificationFeedbackDTO.setNotificationId( notificationFeedback.getNotification().getId() );
            return ResponseEntity.ok().body( notificationFeedbackDTO );
        }
        return ResponseEntity.badRequest().body( "An error has occurred" );
    }

    @PreAuthorize( "hasRole('ROLE_USER')" )
    @GetMapping( "/get-notification-feedback" )
    public NotificationFeedbackDTO getNotificationFeedback(@RequestBody NotificationMobileDTO noti){
        NotificationFeedback notificationFeedback = notificationFeedbackService.getNotificationFeedback(noti.getId());
        if(notificationFeedback == null)
            return null;
        NotificationFeedbackDTO dto = new NotificationFeedbackDTO();
        dto.setDescription(notificationFeedback.getDescription());
        dto.setImageURL(notificationFeedback.getImageURL());
        dto.setId(notificationFeedback.getNotification().getId());
        dto.setId(notificationFeedback.getId());
        return dto;
    }

    @GetMapping( "/camera/get-notification-feedback" )
    public NotificationFeedbackDTO test(@RequestBody NotificationMobileDTO notiID){
        NotificationFeedback notificationFeedback = notificationFeedbackService.getNotificationFeedback(notiID.getId());
        if(notificationFeedback == null)
            return null;
        NotificationFeedbackDTO dto = new NotificationFeedbackDTO();
        dto.setDescription(notificationFeedback.getDescription());
        dto.setImageURL(notificationFeedback.getImageURL());
        dto.setId(notificationFeedback.getNotification().getId());
        dto.setId(notificationFeedback.getId());
        return dto;
    }
}
