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

    public Boolean getReject() {
        return isReject;
    }

    public void setReject( Boolean reject ) {
        isReject = reject;
    }

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

    public List<FeedbackPhoto> getFeedbackPhotoList() {
        return feedbackPhotoList;
    }

    public void setFeedbackPhotoList( List<FeedbackPhoto> feedbackPhotoList ) {
        this.feedbackPhotoList = feedbackPhotoList;
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
                ", feedbackDescription='" + feedbackDescription + '\'' +
                ", time=" + time +
                ", user=" + user +
                '}' + "\n";
    }
    public long getIdPhotoFristReject(){
        for ( int i = 0 ; i <this.feedbackPhotoList.size();i++ ){
            if(!this.feedbackPhotoList.get( i ).isReject())return this.feedbackPhotoList.get( i ).getId();
        }
        return 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }
}
