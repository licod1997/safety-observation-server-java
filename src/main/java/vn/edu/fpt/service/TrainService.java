package vn.edu.fpt.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.entity.TrainFile;

import java.util.List;

public interface TrainService {

List<TrainFile> getAllFileNotTrainYet();
TrainFile setTrain(long trainFileId);
String uploadTrainFile( MultipartFile []files );
TrainFile addFile(TrainFile file);
}
