package vn.edu.fpt.dto;

public class AuthTokenDTO {
    private String token;
    private long expire;

    public AuthTokenDTO() {
    }

    public AuthTokenDTO( String token, long expire ) {
        this.token = token;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire( long expire ) {
        this.expire = expire;
    }
}