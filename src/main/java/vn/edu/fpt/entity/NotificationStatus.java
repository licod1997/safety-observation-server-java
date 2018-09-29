package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.List;

@Table
@Entity( name = "notification_status" )
public class NotificationStatus {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "status_name" )
    private String statusName;
    @OneToMany( mappedBy = "notificationStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Notification.class )
    private List<Notification> notificationList;

    public NotificationStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName( String statusName ) {
        this.statusName = statusName;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList( List<Notification> notificationList ) {
        this.notificationList = notificationList;
    }

    @Override
    public String toString() {
        return "NotificationStatus{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                ", notificationList=" + notificationList +
                '}';
    }
}
