package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.dto.FeedbackPhotoDTO;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.entity.FeedbackPhoto;
import vn.edu.fpt.repository.FeedbackPhotoRepository;
import vn.edu.fpt.repository.FeedbackRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    //Setting Location store img
    private Path fileStorageLocation = Paths.get( "/upload/" ).toAbsolutePath().normalize();

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedbackPhotoRepository feedbackPhotoRepository;

    @Transactional
    @Override
    public String sendFeedback(String description, FeedbackPhotoDTO[] listFeedbackPhotoDTO, Long time) {



        if (listFeedbackPhotoDTO.length == 0  || listFeedbackPhotoDTO == null)return "empty_list_image";
        //check exist image
        for(int i = 0; i < listFeedbackPhotoDTO.length; i++){
            if(feedbackPhotoRepository.findByPhotoName(listFeedbackPhotoDTO[i].getPhotoName())!= null){
                return "exist_image";
            }
        }


        //convert feedbackphotoDTO to feedbackphoto entities
        List<FeedbackPhoto> listFeedbackPhoto = new ArrayList<>();

        int size = listFeedbackPhotoDTO.length;

        for(int i = 0 ;i < listFeedbackPhotoDTO.length ; i++){
            FeedbackPhoto feedbackPhoto = new FeedbackPhoto();

            feedbackPhoto.setPhotoDirectory(listFeedbackPhotoDTO[i].getPhotoDirectory());

            feedbackPhoto.setPhotoName(listFeedbackPhotoDTO[i].getPhotoName());
            listFeedbackPhoto.add(feedbackPhoto);
        }

        //save feedback
        Feedback feedback = new Feedback();

        feedback.setTime(new Date(time));
        feedback.setFeedbackPhotoList(listFeedbackPhoto);
        feedback.setFeedbackDescription(description.trim());

        feedbackRepository.saveAndFlush(feedback);


        //save feedbackphoto;

        for(int i=0; i<listFeedbackPhoto.size();i++){
            listFeedbackPhoto.get(i).setFeedback(feedback);
            listFeedbackPhoto.get(i).setPhotoDirectory(fileStorageLocation.toString());
            feedbackPhotoRepository.save(listFeedbackPhoto.get(i));
        }

        return "send_feedback_successfully";
    }

    @Override
    public String uploadImage(MultipartFile img) throws RuntimeException, IOException {


        String fileName = StringUtils.cleanPath(img.getOriginalFilename());
        Files.createDirectories(fileStorageLocation);

        Path targetLocation = fileStorageLocation.resolve(fileName);
        Files.copy(img.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return  "upload_image_successfully";

    }

    @Override
    public List<Feedback> getFeedbacksLoadPage() {
        return feedbackRepository.findTop10ByOrderByIdDesc();
    }

    @Override
    public List<Feedback> getFeedbacksFirstPage( Long firstNotificationId ) {
        return feedbackRepository.findByIdGreaterThan( firstNotificationId );
    }

    @Override
    public List<Feedback> getFeedbacksLastPage( Long lastNotificationId ) {
        return feedbackRepository.findTop10ByIdLessThanOrderByIdDesc( lastNotificationId );
    }

    @Override
    public Feedback setFeedbackRead( Long currentNotificationId ) {
        Feedback feedback = feedbackRepository.findById( currentNotificationId );
        if ( feedback != null ) {
            feedback.setRead( true );
            return feedbackRepository.save( feedback );
        }
        return null;
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

//    @Override
//    public Feedback setFeedbackReject( Long feedbackId ) {
//        Feedback feedback = feedbackRepository.findById( feedbackId );
//        if(feedback!=null){
//            feedback.setReject( true );
//            return feedbackRepository.saveAndFlush( feedback );
//        }
//
//        return null;
//    }
//
//    @Override
//    public FeedbackPhoto setPhotoReject( Long feedbackPhotoId ) {
//        FeedbackPhoto feedbackPhoto = feedbackPhotoRepository.findById( feedbackPhotoId );
//        if(feedbackPhoto!= null){
//            feedbackPhoto.setRejet( true );
//            return feedbackPhotoRepository.saveAndFlush( feedbackPhoto );
//        }
//        return null;
//    }
}
