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
    @Column( name = "location" )
    private String location;
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "time" )
    private Date time;
    @Column( name = "status" )
    private String status;
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = NotificationStatus.class )
    @JoinColumn( name = "notification_status_id", referencedColumnName = "id" )
    private NotificationStatus notificationStatus;

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public Date getTime() {
        return time;
    }

    public void setTime( Date time ) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus( NotificationStatus notificationStatus ) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", notificationStatus=" + notificationStatus +
                '}';
    }
}
