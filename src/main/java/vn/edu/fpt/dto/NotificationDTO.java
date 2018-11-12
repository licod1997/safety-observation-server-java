package vn.edu.fpt.dto;


import java.util.Date;

public class NotificationDTO {
    private String image_url;
    private Date datetime;
    private Long cameraID;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getCameraID() {
        return cameraID;
    }

    public void setCameraID(Long cameraID) {
        this.cameraID = cameraID;
    }
}
