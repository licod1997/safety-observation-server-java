package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.dto.NotificationFeedbackDTO;
import vn.edu.fpt.entity.Notification;
import vn.edu.fpt.entity.NotificationFeedback;
import vn.edu.fpt.repository.NotificationFeedbackRepository;
import vn.edu.fpt.repository.NotificationResponsitory;

@Service
public class NotificationFeedbackServiceImpl implements NotificationFeedbackService {
    @Autowired
    private NotificationFeedbackRepository notificationFeedbackRepository;
    @Autowired
    private NotificationResponsitory notificationResponsitory;

    @Override
    public NotificationFeedback createNotificationFeedback(String description, Long notiID, String imageURL ) {
        Notification notification = notificationResponsitory.findById( notiID);
        NotificationFeedback notificationFeedback = new NotificationFeedback();
        if ( notification != null ) {
            notificationFeedback.setDescription( description );
            notificationFeedback.setImageURL(imageURL);
            notificationFeedback.setNotification(notification);
            notification.setStatus(1);
            notificationResponsitory.save(notification);
            return notificationFeedbackRepository.save( notificationFeedback );
        }
        return null;
    }
}
