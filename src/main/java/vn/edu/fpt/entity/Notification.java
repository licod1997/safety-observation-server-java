package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity( name = "notification" )
public class Notification {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;

    @Column( name = "image_url" )
    private String imageURL;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "time" )
    private Date time;

    @Column( name = "status" )
    private int status;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = CameraLocation.class )
    @JoinColumn( name = "camera_location", referencedColumnName = "id" )
    private CameraLocation cameraLocation;


    public Notification() {
    }


    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", location='" + cameraLocation + '\'' +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", notificationStatus=" + status +
                '}';
    }
}
