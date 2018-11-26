package vn.edu.fpt.service;

import vn.edu.fpt.dto.NotificationFeedbackDTO;
import vn.edu.fpt.entity.NotificationFeedback;

public interface NotificationFeedbackService {
    NotificationFeedback createNotificationFeedback( NotificationFeedbackDTO notificationFeedbackDTO );
}
