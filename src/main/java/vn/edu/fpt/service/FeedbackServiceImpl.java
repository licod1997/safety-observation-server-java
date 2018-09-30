package vn.edu.fpt.service;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.dto.FeedbackDTO;
import vn.edu.fpt.dto.FeedbackPhotoDTO;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.entity.FeedbackPhoto;
import vn.edu.fpt.repository.FeedbackPhotoRepository;
import vn.edu.fpt.repository.FeedbackRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static String UPLOADED_FOLDER = "C:/upload/";

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedbackPhotoRepository feedbackPhotoRepository;

    @Transactional
    @Override
    public String sendFeedback(String description, FeedbackPhotoDTO[] listFeedbackPhotoDTO, Long time) {

//        if (description.equals("")|| listFeedbackPhotoDTO == null ){
//            return "Some thing went wrong... Send feedback failed";
//        }

        if (listFeedbackPhotoDTO.length == 0  || listFeedbackPhotoDTO == null)return "List photo is empty";

        //convert feedbackphotoDTO to feedbackphoto entities
        List<FeedbackPhoto> listFeedbackPhoto = new ArrayList<>();

        int size = listFeedbackPhotoDTO.length;
        for(int i = 0 ;i < listFeedbackPhotoDTO.length ; i++){
            FeedbackPhoto feedbackPhoto =new FeedbackPhoto();

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
            listFeedbackPhoto.get(i).setPhotoDirectory(UPLOADED_FOLDER);
            feedbackPhotoRepository.save(listFeedbackPhoto.get(i));
        }

        return "Send feedback successfully";
    }

    @Override
    public String uploadImage(MultipartFile img) throws IOException {
        byte[] bytes = img.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + img.getOriginalFilename());
        Files.write(path, bytes);
        return "Upload Image on server";
    }




}
