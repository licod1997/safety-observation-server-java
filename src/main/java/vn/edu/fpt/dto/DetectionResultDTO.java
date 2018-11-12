package vn.edu.fpt.dto;

public class DetectionResultDTO {
    public String tag;
    public Double score;

    public DetectionResultDTO() {
    }

    public DetectionResultDTO( String tag, Double score ) {
        this.tag = tag;
        this.score = score;
    }

    public String getTag() {
        return tag;
    }

    public void setTag( String tag ) {
        this.tag = tag;
    }

    public Double getScore() {
        return score;
    }

    public void setScore( Double score ) {
        this.score = score;
    }
}
