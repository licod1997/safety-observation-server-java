package vn.edu.fpt.dto;

public class NotificationDTO {
    private String imgae;
    private String name;
    private String location;

    public NotificationDTO() {
    }

    public NotificationDTO(String imgae, String name, String location) {
        this.imgae = imgae;
        this.name = name;
        this.location = location;
    }

    public String getImgae() {
        return imgae;
    }

    public void setImgae(String imgae) {
        this.imgae = imgae;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
