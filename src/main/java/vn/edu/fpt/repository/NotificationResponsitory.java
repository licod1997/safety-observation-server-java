package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.entity.Notification;

import java.util.List;

public interface NotificationResponsitory extends JpaRepository<Notification,Long> {
    Notification findById( Long id );

    List<Notification> findNotificationByStatus(int status);
}
