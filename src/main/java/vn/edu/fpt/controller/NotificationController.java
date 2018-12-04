package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.dto.NotificationDTO;
import vn.edu.fpt.dto.NotificationFeedbackDTO;
import vn.edu.fpt.entity.Notification;
import vn.edu.fpt.entity.NotificationFeedback;
import vn.edu.fpt.service.NotificationFeedbackService;
import vn.edu.fpt.service.NotificationService;

import java.util.Date;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @Autowired
    private NotificationFeedbackService notificationFeedbackService;

//    @PostMapping(value = "/sendNotification"
//            , consumes = "application/json",
//            produces = "application/json"
//    )
//    public Notification sendNotification(@RequestBody NotificationDTO notificationDTO) {
//        long time = a.getTime();
//        return notificationService.sendNotification(notificationDTO.getImage_url(), notificationDTO.getCameraID(), time);
//    }

    @PreAuthorize( "hasRole('ROLE_USER')" )
    @GetMapping(value = "/getAllListNotification",
            consumes = "application/json",
            produces = "application/json")
    public List<Notification> getListNotificationByStatus() {
        return notificationService.getAllNotification();
    }

    @PostMapping(value = " /camera/createNotification")
    public String createNotification(NotificationDTO noti){
        return notificationService.createNotification(noti.getImage_url(),noti.getCameraID(),noti.getDatetime());
    }


}
