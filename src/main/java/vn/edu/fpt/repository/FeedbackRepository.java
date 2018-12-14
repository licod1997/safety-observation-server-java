package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.Feedback;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findTop10ByOrderByIdDesc();

    List<Feedback> findByIsRejectFalseAndIdGreaterThan( Long firstNotificationId );

    List<Feedback> findTop10ByIsRejectFalseAndIdLessThanOrderByIdDesc( Long lastNotificationId );

    List<Feedback> findTop10ByIsRejectFalseOrderByIdDesc();

    Feedback findById( Long id );
//add new for load more and rejcet approve button
    List<Feedback> findTop10ByIsRejectIsNullOrIsRejectIsFalseOrderByIdDesc();

    List<Feedback> findByIsRejectIsTrueAndIdGreaterThan( Long firstNotificationId );

    List<Feedback> findTop10ByIsRejectIsTrueAndIdLessThanOrderByIdDesc( Long lastNotificationId );
}
