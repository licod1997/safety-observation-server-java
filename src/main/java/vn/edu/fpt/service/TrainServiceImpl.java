package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.entity.TrainFile;
import vn.edu.fpt.repository.TrainFileRepository;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService{
    @Autowired
    TrainFileRepository trainFileRepository;
    @Override
    public List<TrainFile> getAllFileNotTrainYet() {
        return trainFileRepository.findAllByIsTrainIsFalse();
    }

    @Override
    public TrainFile setTrain( long trainFileId ) {
        TrainFile trainFile = trainFileRepository.findById( trainFileId );
        if(trainFile!= null){
            trainFile.setTrain( true );
            return trainFileRepository.saveAndFlush( trainFile );
        }
        return null;
    }

    @Override
    public String uploadTrainFile( MultipartFile[] files ) {
        return null;
    }

    @Override
    public TrainFile addFile( TrainFile file ) {
        TrainFile fileInDB = trainFileRepository.findByFileName( file.getFileName() );
        if(fileInDB == null){
            return trainFileRepository.saveAndFlush( file );
        }
        fileInDB.setTrain( false );
        return trainFileRepository.saveAndFlush( fileInDB );
    }
}
