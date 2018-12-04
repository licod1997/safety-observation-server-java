package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.dto.NotificationDTO;
import vn.edu.fpt.dto.NotificationFeedbackDTO;
import vn.edu.fpt.dto.NotificationMobileDTO;
import vn.edu.fpt.entity.Notification;
import vn.edu.fpt.entity.NotificationFeedback;
import vn.edu.fpt.service.NotificationFeedbackService;
import vn.edu.fpt.service.NotificationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/getAllListNotification")
    public List<Notification> getListNotificationByStatus() {
        return notificationService.getAllNotification();
    }

    @PostMapping(value = "/camera/createNotification")
    public String createNotification(@RequestBody NotificationDTO noti) {
        System.out.println(noti.getImage_url());
        return notificationService.createNotification(noti.getImage_url(), noti.getCameraID(), noti.getDatetime());
    }

    @GetMapping(value = "/getAllNotification")
    public List<NotificationMobileDTO> getAllNotification() {
        List<Notification> list = notificationService.getAllNotification();
        List<NotificationMobileDTO> notilist = new ArrayList<>();
        for (Notification noti : list) {
            NotificationMobileDTO notiFormat = new NotificationMobileDTO();
            notiFormat.setId(noti.getId());
            notiFormat.setDatetime(noti.getTime().getTime());
            notiFormat.setImage_url(noti.getImageURL());
            notiFormat.setStatus(noti.getStatus());
            notiFormat.setCamera_location(noti.getCameraLocation().getLocation());
            if (!(noti.getNotificationFeedback() == null)) {
                notiFormat.setNotiFeedback(noti.getNotificationFeedback().getId());

            }
            if (!(noti.getUser() == null)) {
                notiFormat.setUsername(noti.getUser().getUsername());
            }
            notilist.add(notiFormat);
        }
        return notilist;
    }

}
