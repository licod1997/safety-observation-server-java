package vn.edu.fpt.service;

import vn.edu.fpt.entity.Notification;

import java.sql.Date;

public interface NotificationService {
    String sendNotification(String image_url, int cameraID, Date datetime);

    Notification rejectNotification(Long id, int userID);

    Notification doneNotification(Long id, int userID, String image_url);


}
