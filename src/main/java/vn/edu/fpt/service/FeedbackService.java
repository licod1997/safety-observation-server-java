package vn.edu.fpt.service;

        import org.springframework.web.multipart.MultipartFile;
        import vn.edu.fpt.dto.FeedbackPhotoDTO;

        import java.io.File;
        import java.io.IOException;
        import java.util.Date;
        import java.util.List;



public interface FeedbackService {
    String sendFeedback(String description , FeedbackPhotoDTO[] listFeedbackPhotoDTO , Long time);

    String uploadImage(MultipartFile img) throws IOException;
}
