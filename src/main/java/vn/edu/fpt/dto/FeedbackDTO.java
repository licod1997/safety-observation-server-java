package vn.edu.fpt.dto;


import java.util.Date;
import java.util.List;

public class FeedbackDTO {
    private String feedbackDescription;
    private Date time;
    private FeedbackPhotoDTO[] feedbackPhotoList; //???? request gửi hình vào list này à?

    public FeedbackDTO() {
    }

    public FeedbackDTO(String feedbackDescription, Date time, FeedbackPhotoDTO[] feedbackPhotoList) {
        this.feedbackDescription = feedbackDescription;
        this.time = time;
        this.feedbackPhotoList = feedbackPhotoList;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "feedbackDescription='" + feedbackDescription + '\'' +
                ", time=" + time +
                ", feedbackPhotoList=" + feedbackPhotoList +
                '}';
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public FeedbackPhotoDTO[] getFeedbackPhotoList() {
        return feedbackPhotoList;
    }

    public void setFeedbackPhotoList(FeedbackPhotoDTO[] feedbackPhotoList) {
        this.feedbackPhotoList = feedbackPhotoList;
    }
}
