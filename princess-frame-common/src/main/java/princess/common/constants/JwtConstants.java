package princess.common.constants;

public class JwtConstants {

    public interface Type {
        String JWT = "JWT";
    }

    public interface Header {
        String TYP = "typ";
        String ALG = "alg";
    }

    public interface Secret {
        String DEFAULT_SECRET = "huacloud.jwdsj";
    }

    public interface PayloadParams {
        String TOKEN_KEY = "token";
        String PRINCIPAL = "principal";
    }


}
