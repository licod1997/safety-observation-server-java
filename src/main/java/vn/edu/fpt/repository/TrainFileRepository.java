package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.TrainFile;

import java.util.List;

@Repository
public interface TrainFileRepository extends JpaRepository<TrainFile, Long> {
    TrainFile findById(Long trainFileId);
    List<TrainFile> findAllByIsTrainIsFalse();
    TrainFile findByFileName(String filename);
}
