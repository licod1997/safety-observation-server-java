package vn.edu.fpt.dto;

import vn.edu.fpt.entity.CameraLocation;
import vn.edu.fpt.entity.NotificationFeedback;
import vn.edu.fpt.entity.User;

import javax.persistence.*;
import java.util.Date;

public class NotificationMobileDTO {
    private Long id;
    private String image_url;
    private Long datetime;
    private int status;
    private String camera_location;
    private String username;
    private Long notiFeedback;

    public NotificationMobileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCamera_location() {
        return camera_location;
    }

    public void setCamera_location(String camera_location) {
        this.camera_location = camera_location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getNotiFeedback() {
        return notiFeedback;
    }

    public void setNotiFeedback(Long notiFeedback) {
        this.notiFeedback = notiFeedback;
    }
}
