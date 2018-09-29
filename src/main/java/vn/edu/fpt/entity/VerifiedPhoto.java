package vn.edu.fpt.entity;

import javax.persistence.*;

@Table
@Entity( name = "verified_photo" )
public class VerifiedPhoto {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "photo_name" )
    private String photoName;
    @Column( name = "photo_directory" )
    private String photoDirectory;
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Tag.class )
    @JoinColumn( name = "verified_photo_id", referencedColumnName = "id" )
    private Tag tag;

    public VerifiedPhoto() {
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

    public Tag getTag() {
        return tag;
    }

    public void setTag( Tag tag ) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "VerifiedPhoto{" +
                "id=" + id +
                ", photoName='" + photoName + '\'' +
                ", photoDirectory='" + photoDirectory + '\'' +
                ", tag=" + tag +
                '}';
    }
}
