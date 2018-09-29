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
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Feedback.class )
    @JoinColumn( name = "feedback_id", referencedColumnName = "id" )
    private Feedback feedback;

    public FeedbackPhoto() {
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

    @Override
    public String toString() {
        return "FeedbackPhoto{" +
                "id=" + id +
                ", photoName='" + photoName + '\'' +
                ", photoDirectory='" + photoDirectory + '\'' +
                ", feedback=" + feedback +
                '}';
    }
}
