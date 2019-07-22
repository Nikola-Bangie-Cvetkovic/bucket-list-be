package bucketlist.server.security;

class SecurityConstants {

    static final String SECRET = "jwthack";
    static final Integer EXPIRATION_DATE = 300 * 1000; //5min
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/clients";

}
