package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.dto.NotificationDTO;
import vn.edu.fpt.entity.Notification;
import vn.edu.fpt.service.NotificationService;

import java.util.Date;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @PostMapping(value = "/sendNotification"
            , consumes = "application/json",
            produces = "application/json"
    )
    public Notification sendNotification(@RequestBody NotificationDTO notificationDTO) {
        Date a = notificationDTO.getDatetime();
        long time = a.getTime();
        return notificationService.sendNotification(notificationDTO.getImage_url(), notificationDTO.getCameraID(), time);
    }

    @GetMapping(value = "/getAllListNotification",
            consumes = "application/json",
            produces = "application/json")
    public List<Notification> getListNotificationByStatus() {
        return notificationService.getAllNotification();
    }
}
