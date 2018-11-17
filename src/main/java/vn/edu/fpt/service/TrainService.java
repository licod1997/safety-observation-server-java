package vn.edu.fpt.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.entity.TrainFile;

import java.util.List;

public interface TrainService extends Runnable {

List<TrainingFile> getAllFileNotTrainYet();

TrainingFile setTrain(long trainingFileId);

TrainingFile addFile(TrainingFile file);
    void stopProcessBuilder();
}
