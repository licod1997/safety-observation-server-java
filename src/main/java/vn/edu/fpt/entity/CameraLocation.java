package vn.edu.fpt.entity;

import javax.persistence.*;

@Table
@Entity(name = "camera_location")
public class CameraLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "location", unique = true)
    private String location;

    public CameraLocation() {
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
