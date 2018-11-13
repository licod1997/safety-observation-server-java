package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.CameraLocation;
import vn.edu.fpt.entity.Notification;
import vn.edu.fpt.repository.CameraLocationRepository;
import vn.edu.fpt.repository.NotificationResponsitory;
import vn.edu.fpt.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationResponsitory notificationResponsitory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CameraLocationRepository cameraLocationRepository;

    @Transactional
    @Override
    public Notification sendNotification(String image_url, Long cameraID, Long datetime) {
        CameraLocation cameraLocation = cameraLocationRepository.findById(cameraID);
        if(cameraLocation == null)
            return null;

        Notification notification = new Notification();
        notification.setImageURL(image_url);
        notification.setCameraLocation(cameraLocation);
        notification.setStatus(0);// status = 0, notification is not read
        notification.setTime(new Date(datetime));

        notificationResponsitory.save(notification);
        return notification;
    }

    @Override
    public Notification rejectNotification(Long id, Long userID) {
        return null;
    }

    @Override
    public Notification doneNotification(Long id, Long userID, String image_url) {
        return null;
    }

    @Override
    public List<Notification> getListNotificationByStatus(int status) {
        return notificationResponsitory.findNotificationByStatus(status);
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationResponsitory.findAll();
    }
}
