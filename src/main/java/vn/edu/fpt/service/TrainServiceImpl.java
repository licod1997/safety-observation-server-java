package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.TrainingFile;
import vn.edu.fpt.repository.TrainingFileRepository;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService{
    @Autowired
    TrainingFileRepository trainingFileRepository;
    @Override
    public List<TrainingFile> getAllFileNotTrainYet() {
        return trainingFileRepository.findAllByIsTrainIsFalse();
    }

    @Override
    public TrainingFile setTrain( long trainFileId ) {
        TrainingFile trainingFile = trainingFileRepository.findById( trainFileId );
        if(trainingFile!= null){
            trainingFile.setTrain( true );
            return trainingFileRepository.saveAndFlush( trainingFile );
        }
        return null;
    }



    @Override
    public TrainingFile addFile( TrainingFile file ) {
        TrainingFile fileInDB = trainingFileRepository.findByFileName( file.getFileName() );
        if(fileInDB == null){
            return trainingFileRepository.saveAndFlush( file );
        }
        fileInDB.setTrain( false );
        return trainingFileRepository.saveAndFlush( fileInDB );
    }
}
