package vn.edu.fpt.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.dto.FeedbackPhotoDTO;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.entity.FeedbackPhoto;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public interface FeedbackService {
    String sendFeedback(String description, FeedbackPhotoDTO[] listFeedbackPhotoDTO, Date time, String username );

    String uploadImage( MultipartFile img ) throws IOException;

    List<Feedback> getFeedbacksLoadPage();

    List<Feedback> getFeedbacksFirstPage( Long firstNotificationId );

    List<Feedback> getFeedbacksLastPage( Long lastNotificationId );

    Feedback setFeedbackRead( Long currentNotificationId );

    Feedback getFeedbackById(Long feedbackId);


    Feedback setFeedbackReject(Long feedbackId, boolean setOtion);

    FeedbackPhoto setPhotoReject(Long feedbackPhotoId,boolean setOption);
}
