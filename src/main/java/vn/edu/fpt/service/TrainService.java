package vn.edu.fpt.service;

import vn.edu.fpt.entity.TrainingFile;

import java.util.List;

public interface TrainService extends Runnable {

List<TrainingFile> getAllFileNotTrainYet();

TrainingFile setTrain(long trainingFileId);

TrainingFile addFile(TrainingFile file);
    void stopProcessBuilder();
}
