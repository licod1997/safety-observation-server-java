package vn.edu.fpt.service;

import vn.edu.fpt.entity.Notification;

import java.util.Date;
import java.util.List;


public interface NotificationService {
    Notification sendNotification(String image_url, Long cameraID, Long datetime);

    Notification rejectNotification(Long id, Long userID);

    Notification doneNotification(Long id, Long userID, String image_url);

    List<Notification> getListNotificationByStatus(int status);

    List<Notification> getAllNotification();
}
