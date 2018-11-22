package vn.edu.fpt.entity;

import javax.persistence.*;

@Table
@Entity( name = "feedback_photo" )
public class FeedbackPhoto {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "photo_name" )
    private String photoName;
    @Column( name = "photo_directory" )
    private String photoDirectory;
    @Column( name = "is_reject" )
    private Boolean isReject;
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Feedback.class )
    @JoinColumn( name = "feedback_id", referencedColumnName = "id" )
    private Feedback feedback;

    public FeedbackPhoto() {
        this.isReject=false;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName( String photoName ) {
        this.photoName = photoName;
    }

    public String getPhotoDirectory() {
        return photoDirectory;
    }

    public void setPhotoDirectory( String photoDirectory ) {
        this.photoDirectory = photoDirectory;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback( Feedback feedback ) {
        this.feedback = feedback;
    }

    public boolean isReject() {
        return isReject;
    }

    public void setReject( boolean reject ) {
        isReject = reject;
    }

}
