package vn.edu.fpt.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.entity.TrainingFile;

import java.util.List;

public interface TrainService {

List<TrainingFile> getAllFileNotTrainYet();

TrainingFile setTrain(long trainingFileId);

TrainingFile addFile(TrainingFile file);
}
