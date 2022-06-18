package cu.uci.cegel.oauth2server.config;

public class SecurityConstants {

    public static final String CLIENT_ID = "oauth2";
    public static final String SECRET = "oauth2secret";
    public static final String SIGQUO_RESOURCE_ID = "sigipId";
    public static final String SIGQUO_BASE_RESOURCE_ID = "sigipbaseId";
    public static final String OAUTH2_RESOURCE_ID = "oauth2Id";
    public static final int ACCESS_TOKEN_VALIDITY = 3600;
    public static final int REFRESH_TOKEN_VALIDITY = 2592000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private SecurityConstants(){
        throw new IllegalStateException("Utility class");
    }
}
