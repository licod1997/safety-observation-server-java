package vn.edu.fpt.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.dto.FeedbackPhotoDTO;
import vn.edu.fpt.entity.Feedback;

import java.io.IOException;
import java.util.List;


public interface FeedbackService {
    String sendFeedback( String description, FeedbackPhotoDTO[] listFeedbackPhotoDTO, Long time );

    String uploadImage( MultipartFile img ) throws IOException;

    List<Feedback> getFeedbacksLoadPage();

    List<Feedback> getFeedbacksFirstPage( Long firstNotificationId );

    List<Feedback> getFeedbacksLastPage( Long lastNotificationId );

    Feedback setFeedbackRead( Long currentNotificationId );
}
