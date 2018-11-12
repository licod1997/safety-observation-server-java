package vn.edu.fpt.entity;

import javax.persistence.*;

@Table
@Entity( name = "train_file" )
public class TrainFile {
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


    public TrainFile() {
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

    @Override
    public String toString() {
        return "TrainFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileDirectory='" + fileDirectory + '\'' +
                ", isTrain=" + isTrain +
                '}';
    }
}
