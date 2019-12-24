package huacloud.jwdsj.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import huacloud.jwdsj.common.security.AuthRealm;
import huacloud.jwdsj.common.security.SecurityImplementor;
import princess.common.security.Securitys;
import princess.common.security.jwt.JwtComponent;
import princess.common.security.jwt.JwtComponentImpl;

/**
 * 项目公用配置
 * @author YYL
 */
@Configuration
public class GlobalConfiguration {

    /** 权限信息工具 */
    @Primary
    @Bean
    public Securitys securitys() {
        return new SecurityImplementor();
    }

    /** 密码编码器 */
    @Primary
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /** 认证领域类 */
    @Bean
    public AuthRealm authRealm() {
        return new AuthRealm();
    }

    /** JWT组件 */
    @Bean
    public JwtComponent jwtComponent() {
        return new JwtComponentImpl();
    }
}
