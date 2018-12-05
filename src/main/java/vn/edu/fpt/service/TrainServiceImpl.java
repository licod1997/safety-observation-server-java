package vn.edu.fpt.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.TrainingFile;
import vn.edu.fpt.repository.TrainingFileRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
    Logger logger = LoggerFactory.getLogger( TrainServiceImpl.class );
    private static String PATH_TO_TRAINING_DIR = "C:/Users/Notebook/Desktop/train_model/models/research/object_detection/";
    private Process process;
    private boolean breakCondition = false;
    @Autowired
    TrainingFileRepository trainingFileRepository;

    @Override
    public List<TrainingFile> getAllFileNotTrainYet() {
        return trainingFileRepository.findAllByIsTrainIsFalse();
    }

    @Override
    public TrainingFile setTrain( long trainFileId ) {
        TrainingFile trainingFile = trainingFileRepository.findById( trainFileId );
        if ( trainingFile != null ) {
            trainingFile.setTrain( true );
            return trainingFileRepository.saveAndFlush( trainingFile );
        }
        return null;
    }

    @Override
    public TrainingFile addFile( TrainingFile file ) {
        TrainingFile fileInDB = trainingFileRepository.findByFileName( file.getFileName() );
        if ( fileInDB == null ) {
            return trainingFileRepository.saveAndFlush( file );
        }
        fileInDB.setTrain( false );
        return trainingFileRepository.saveAndFlush( fileInDB );
    }

    @Override
    public void run() {
        String[] commands = {"rm -rf train",
                "mkdir train",
                "rm -rf dangerous_thing_graph",
                "mkdir dangerous_thing_graph",
                "python xml_to_csv.py",
                "python generate_tfrecord.py --csv_input=data/train_labels.csv  --output_path=data/train.record",
                "python generate_tfrecord.py --csv_input=data/test_labels.csv  --output_path=data/test.record",
                "python model_main.py --pipeline_config_path=training/faster_rcnn_resnet101_coco.config --model_dir=train/ --num_train_steps=50000 --logtostderr --sample_1_of_n_eval_examples=1",
                "python export_inference_graph.py --input_type image_tensor --pipeline_config_path training/faster_rcnn_resnet101_coco.config  --trained_checkpoint_prefix train/model.ckpt-50000 --output_directory dangerous_thing_graph"
        };
        try {
            for ( String cmd : commands ) {
                exec( cmd, PATH_TO_TRAINING_DIR );
                if ( breakCondition ) break;
            }
        } catch ( IOException e ) {
            logger.error( "TrainServiceImpl: " + e );
        } catch ( InterruptedException e ) {
            logger.error( "TrainServiceImpl: " + e );
        }
        breakCondition = false;
    }

    public void stopProcessBuilder() {
        System.out.println( 1 );
        breakCondition = true;
        process.destroy();
        process.destroyForcibly();
    }

    private void exec( String cmd, String dir ) throws IOException, InterruptedException {
        logger.info( cmd );
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe",
                "/c",
                cmd );
        builder.directory( new File( dir ) );
        builder.redirectOutput( ProcessBuilder.Redirect.INHERIT );
        builder.redirectError( ProcessBuilder.Redirect.INHERIT );
        process = builder.start();
        process.waitFor();
        BufferedReader br = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
        String line;
        while ( true && !breakCondition ) {
            line = br.readLine();
            if ( line == null ) break;
            logger.info( line );
        }
        process.destroy();
    }

    @Override
    public void preprocessBeforeTraining() {
        List<TrainingFile> trainingFileList = trainingFileRepository.findAllByIsTrainIsFalse();
        for ( TrainingFile trainingFile : trainingFileList ) {
            File xmlFile = new File( trainingFile.getFileDirectory() + "\\" + trainingFile.getFileName() );
            File jpgFile = new File( trainingFile.getFileDirectory() + "\\" + FilenameUtils.removeExtension( trainingFile.getFileName() ) + ".jpg" );

            File xmlFileTrain = new File( PATH_TO_TRAINING_DIR + "\\images\\train\\" + trainingFile.getFileName() );
            File jpgFileTrain = new File( PATH_TO_TRAINING_DIR + "\\images\\train\\" + FilenameUtils.removeExtension( trainingFile.getFileName() ) + ".jpg" );
            File xmlFileImg = new File( PATH_TO_TRAINING_DIR + "\\images\\" + trainingFile.getFileName() );
            File jpgFileImg = new File( PATH_TO_TRAINING_DIR + "\\images\\" + FilenameUtils.removeExtension( trainingFile.getFileName() ) + ".jpg" );

            if ( xmlFile.exists() && jpgFile.exists() ) {
                try {
                    FileUtils.copyFile( xmlFile, xmlFileTrain );
                    FileUtils.copyFile( xmlFile, xmlFileImg );

                    FileUtils.copyFile( jpgFile, jpgFileTrain );
                    FileUtils.copyFile( jpgFile, jpgFileImg );

                    trainingFile.setTrain( true );
                } catch ( IOException e ) {
                    logger.error( "TrainServiceImpl: " + e );
                }
            }
        }
        trainingFileRepository.save( trainingFileList );
    }
}
