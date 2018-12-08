package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity( name = "training_file" )
public class TrainingFile {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "file_name" )
    private String fileName;
    @Column( name = "file_directory" )
    private String fileDirectory;
    @Column( name = "is_train" )
    private Boolean isTrain;
    @Column( name = "time_upload" )
    private Date timeUpload;
//    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class )
//    @JoinColumn( name = "user_id", referencedColumnName = "id" )
//    private User user;

    public TrainingFile() {
        this.isTrain=false;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory( String fileDirectory ) {
        this.fileDirectory = fileDirectory;
    }

    public Boolean getTrain() {
        return isTrain;
    }

    public void setTrain( Boolean train ) {
        isTrain = train;
    }

    public Date getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload( Date timeUpload ) {
        this.timeUpload = timeUpload;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser( User user ) {
//        this.user = user;
//    }

//    @Override
//    public String
//    toString() {
//        return "TrainingFile{" +
//                "id=" + id +
//                ", fileName='" + fileName + '\'' +
//                ", fileDirectory='" + fileDirectory + '\'' +
//                ", isTrain=" + isTrain +
//                ", timeUpload=" + timeUpload +
//                ", user=" + user +
//                '}';
//    }

    @Override
    public String toString() {
        return "TrainingFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileDirectory='" + fileDirectory + '\'' +
                ", isTrain=" + isTrain +
                ", timeUpload=" + timeUpload +
                '}';
    }
}
