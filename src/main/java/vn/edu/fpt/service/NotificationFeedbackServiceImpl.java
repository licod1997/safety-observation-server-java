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
    public NotificationFeedback createNotificationFeedback( NotificationFeedbackDTO notificationFeedbackDTO ) {
        Notification notification = notificationResponsitory.findById( notificationFeedbackDTO.getNotificationId() );
        NotificationFeedback notificationFeedback = new NotificationFeedback();
        if ( notification != null ) {
            notificationFeedback.setDescription( notificationFeedbackDTO.getDescription() );
            notificationFeedback.setImageURL( notificationFeedbackDTO.getImageURL() );
            notification.setNotificationFeedback( notificationFeedback );
            return notificationFeedbackRepository.save( notificationFeedback );
        }
        return null;
    }
}
