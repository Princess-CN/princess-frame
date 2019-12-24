package princess.common.security.jwt;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import base.identifier.IdUtil;
import base.lang.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import princess.common.constants.JwtConstants;
import princess.common.core.util.json.JsonUtil;
import princess.common.security.Principal;
import princess.common.security.PrincipalGJ;
import princess.common.security.PrincipalYHXX;

@Slf4j
public class JwtComponentImpl implements JwtComponent {

    private SignatureAlgorithm algorithm;
    private byte[] encodedKey;
    private long expireMillis = 24L * 60L * 60L * 1000L;// 86400000

    public JwtComponentImpl() {
        this(JwtConstants.Secret.DEFAULT_SECRET);
    }

    public JwtComponentImpl(String secret) {
        this(SignatureAlgorithm.HS256, secret);
    }

    public JwtComponentImpl(SignatureAlgorithm algorithm, String secret) {
        this.algorithm = algorithm;
        this.encodedKey = Base64.encodeBase64(secret.getBytes());
    }

    @Override
    public String createToken(Principal principal) {
        long currentMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder();
        setDefaultHeader(builder);
        builder.setId(IdUtil.uuid());// JWT ID
        builder.setSubject(encodePrincipal(principal)); // 主题
        builder.setIssuedAt(new Date(currentMillis));// 签发时间
        builder.setExpiration(new Date(currentMillis + expireMillis)); // 过期时间
        setDefaultSignWith(builder); // 签名算法以及密匙
        String token = builder.compact(); // 生成Token
        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        Claims claims = getClaimsByToken(token);
        return claims != null;
    }

    @Override
    public Principal parseToken(String token) {
        Claims claims = getClaimsByToken(token);
        if (claims == null) {
            return null;
        }
        String subject = claims.getSubject();
        return decodePrincipal(subject);
    }

    @Override
    public String postponeCurrentToken(String token) {
        Claims claims = getClaimsByToken(token);
        if (claims == null) {
            return null;
        }
        long currentMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder();
        setDefaultHeader(builder);
        builder.setExpiration(new Date(currentMillis + expireMillis)); // 过期时间
        builder.setClaims(claims);
        setDefaultSignWith(builder);
        return builder.compact();
    }

    @Override
    public PrincipalGJ parseTokenGJ(String token) {
        PrincipalGJ principalGJ = null;
        try {
            principalGJ = JsonUtil.decode(token, PrincipalGJ.class);
            if (!StringUtil.isEmpty(principalGJ.getYhxx())) {
                byte[] decode = Base64.decodeBase64(principalGJ.getYhxx());
                String decoderStr = new String(decode, "UTF-8");
                PrincipalYHXX principalYHXX = JsonUtil.decode(decoderStr, PrincipalYHXX.class);
                principalGJ.setPrincipalYHXX(principalYHXX);
            }
        } catch (Exception e) {
            // throw new GeneralException(ErrorType.DEFAULT, "Base64 decode Excption", e);
        }
        return principalGJ;
    }

    private JwtBuilder setDefaultHeader(JwtBuilder builder) {
        builder.setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE);
        builder.setHeaderParam(JwsHeader.ALGORITHM, algorithm.getValue());
        return builder;
    }

    private JwtBuilder setDefaultSignWith(JwtBuilder builder) {
        builder.signWith(algorithm, encodedKey); // 签名算法以及密匙
        return builder;
    }

    private String encodePrincipal(Principal principal) {
        return JsonUtil.encode(principal);
    }

    private Principal decodePrincipal(String json) {
        return JsonUtil.decode(json, Principal.class);
    }

    private Claims getClaimsByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            JwtParser parser = Jwts.parser().setSigningKey(encodedKey); // 获得JWT解析器
            Jws<Claims> jws = parser.parseClaimsJws(token); // 解析载荷为Claims
            return jws.getBody();// 获得载荷体
        } catch (Exception e) {
            log.warn("!", e);
            return null;
        }
    }
}
