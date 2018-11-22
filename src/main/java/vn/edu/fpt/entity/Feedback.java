package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity( name = "feedback" )
public class Feedback {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "is_reject" )
    private Boolean isReject;
    @Column( name = "feedback_description" )
    private String feedbackDescription;
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "time" )
    private Date time;
    @Column( name = "is_read" )
    private Boolean isRead;
    @OneToMany( mappedBy = "feedback", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = FeedbackPhoto.class )
    private List<FeedbackPhoto> feedbackPhotoList;
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class )
    @JoinColumn( name = "user_id", referencedColumnName = "id" )
    private User user;

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription( String feedbackDescription ) {
        this.feedbackDescription = feedbackDescription;
    }

    public Date getTime() {
        return time;
    }

    public void setTime( Date time ) {
        this.time = time;
    }

    public Boolean getReject() {
        return isReject;
    }

    public void setReject( Boolean reject ) {
        isReject = reject;
    }

    public List<FeedbackPhoto> getFeedbackPhotoList() {
        return feedbackPhotoList;
    }

    public void setFeedbackPhotoList( List<FeedbackPhoto> feedbackPhotoList ) {
        this.feedbackPhotoList = feedbackPhotoList;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead( Boolean read ) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", isReject=" + isReject +
                ", feedbackDescription='" + feedbackDescription + '\'' +
                ", time=" + time +
                ", isRead=" + isRead +
                ", feedbackPhotoList=" + feedbackPhotoList +
                '}';
    }
}
