package vn.edu.fpt.entity;

import javax.persistence.*;

@Table
@Entity( name = "notification_feedback" )
public class NotificationFeedback {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "description" )
    private String description;
    @Column( name = "imageURL" )
    private String imageURL;
    @OneToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = Notification.class, optional = false )
    @JoinColumn( name = "notification_id" )
    private Notification notification;

    public NotificationFeedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL( String imageURL ) {
        this.imageURL = imageURL;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification( Notification notification ) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "NotificationFeedback{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
