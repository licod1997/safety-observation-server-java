package vn.edu.fpt.dto;

public class UserAdministrationDTO {
    private Long userId;
    private Boolean enable;

    public UserAdministrationDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId( Long userId ) {
        this.userId = userId;
    }
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable( Boolean enable ) {
        this.enable = enable;
    }
}
