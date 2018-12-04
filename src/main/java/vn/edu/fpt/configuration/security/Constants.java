package vn.edu.fpt.configuration.security;

public class Constants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;
    public static final String SIGNING_KEY = "IP8X2vcWgOgLK5niDFQjiBJWK3LPNQKS";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "authorities";
    public static final String SIGN_IN_URL = "/dang-nhap";
    public static final String SIGN_UP_URL = "/dang-ky";
    public static final String GENERATE_TOKEN_URL = "/token/*";
    public static final String CAMERA_URL = "/camera/*";
}