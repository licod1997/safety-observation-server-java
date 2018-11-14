package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.TrainingFile;

import java.util.List;

@Repository
public interface TrainingFileRepository extends JpaRepository<TrainingFile, Long> {
    TrainingFile findById(Long trainingFileId);
    List<TrainingFile> findAllByIsTrainIsFalse();
    TrainingFile findByFileName(String filename);
}
