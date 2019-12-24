package huacloud.jwdsj.common.security;

import org.springframework.beans.factory.annotation.Autowired;

import princess.common.security.AuthToken;
import princess.common.security.Principal;
import princess.common.security.PrincipalGJ;
import princess.common.security.Securitys;
import princess.common.security.jwt.JwtComponent;

/**
 * 用户信息提供者
 */
public class SecurityImplementor implements Securitys {

    @Autowired
    private AuthRealm realm;
    @Autowired
    private JwtComponent jwtComponent;

    @Override
    public String login(AuthToken authToken) {
        Principal principal = realm.doGetAuthenticationInfo(authToken);
        String token = jwtComponent.createToken(principal);
        SecurityContextHolder.setPrincipal(principal);
        return token;
    }

    @Override
    public void logout() {
        // 未实现登出
    }

    /**
     * 获得当前登录用户
     * @return 用户信息
     */
    @Override
    public Principal getPrincipal() {
        Principal principal = SecurityContextHolder.getPrincipal();
        return principal == null ? Principal.NONE : principal;
    }

    /**
     * 获得登录用户
     * @param token 用户TOKEN
     * @return 用户信息
     */
    protected Principal parseToken(String token) {
        return jwtComponent.parseToken(token);
    }

    /**
     * 获取高检用户
     * @param token
     * @return
     */
    protected PrincipalGJ parseTokenGJ(String token) {
        return jwtComponent.parseTokenGJ(token);
    }

}
