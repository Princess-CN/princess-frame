package princess.common.security.jwt;

import princess.common.security.Principal;
import princess.common.security.PrincipalGJ;

public interface JwtComponent {

    /**
     * 创建 JWT Token
     * @param principal 用户信息
     * @return JWT Token
     */
    String createToken(Principal principal);

    /**
     * 验证Token
     * @param token JWT Token
     * @return 验证Token成功返回True 验证Token失败返回False
     */
    boolean verifyToken(String token);

    /**
     * 解码Token
     * @param token JWT Token
     * @return 解析Token成功返回Claims对象实例; 解析Token失败返回null对象.
     */
    Principal parseToken(String token);


    /**
     * 延长会话时间
     * @param token JWT Token
     * @return 延期后的JWT Token
     */
    String postponeCurrentToken(String token);

    /**
     * 获取高检用户信息
     * @param token
     * @return
     */
    PrincipalGJ parseTokenGJ(String token);
}
