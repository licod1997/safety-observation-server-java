package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.entity.NotificationFeedback;

public interface NotificationFeedbackRepository extends JpaRepository<NotificationFeedback, Long> {
}
