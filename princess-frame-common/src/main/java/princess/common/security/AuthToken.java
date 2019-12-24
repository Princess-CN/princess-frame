package princess.common.security;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录令牌
 */
@Data
@SuppressWarnings("serial")
public class AuthToken implements Serializable {

    /** 账号 */
    private String username;
    /** 密码 */
    private String password;
}
