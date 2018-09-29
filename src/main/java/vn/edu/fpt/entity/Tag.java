package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.List;

@Table
@Entity( name = "tag" )
public class Tag {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "tag_name" )
    private String tagName;
    @OneToMany( mappedBy = "tag", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = VerifiedPhoto.class )
    private List<VerifiedPhoto> verifiedPhotoList;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName( String tagName ) {
        this.tagName = tagName;
    }

    public List<VerifiedPhoto> getVerifiedPhotoList() {
        return verifiedPhotoList;
    }

    public void setVerifiedPhotoList( List<VerifiedPhoto> verifiedPhotoList ) {
        this.verifiedPhotoList = verifiedPhotoList;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", verifiedPhotoList=" + verifiedPhotoList +
                '}';
    }
}
