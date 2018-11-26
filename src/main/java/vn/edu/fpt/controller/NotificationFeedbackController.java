package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.dto.NotificationFeedbackDTO;
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
        NotificationFeedback notificationFeedback = notificationFeedbackService.createNotificationFeedback( notificationFeedbackDTO );
        if ( notificationFeedback != null ) {
            notificationFeedbackDTO.setId( notificationFeedback.getId() );
            notificationFeedbackDTO.setDescription( notificationFeedback.getDescription() );
            notificationFeedbackDTO.setImageURL( notificationFeedback.getImageURL() );
            notificationFeedbackDTO.setNotificationId( notificationFeedback.getNotification().getId() );
            return ResponseEntity.ok().body( notificationFeedbackDTO );
        }
        return ResponseEntity.badRequest().body( "An error has occurred" );
    }
}
