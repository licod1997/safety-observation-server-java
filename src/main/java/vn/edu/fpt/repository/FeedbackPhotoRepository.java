package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.FeedbackPhoto;
@Repository
public interface FeedbackPhotoRepository extends JpaRepository<FeedbackPhoto,Long> {
    FeedbackPhoto findByPhotoName(String photoName);
}
