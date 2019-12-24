package princess.common.security;


import princess.common.core.expection.GeneralException;

/**
 * 权限信息工具(接口类)
 */
public interface Securitys {

    /**
     * 用户登录
     * @param token 登录凭证
     * @return 返回票据
     * @throws GeneralException 登录失败抛出认证异常
     */
    String login(AuthToken token) throws GeneralException;

    /**
     * 用户登出
     */
    void logout();

    /**
     * 获得当前登录用户
     * @return 当前登录用户
     */
    Principal getPrincipal();
}
