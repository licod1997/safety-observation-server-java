package vn.edu.fpt.dto;

public class CameraLocation {
    private int id;
    private String location;

    public CameraLocation() {
    }

    public CameraLocation(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
