package huacloud.jwdsj.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 自定义属性
 * @author YYL
 */
@Component
@ConfigurationProperties(prefix = "custom.properties")
@Order(0)
@Data
public class CustomProperties {

    /** 用户默认密码 */
    private String defaultUserPassword = "123456";

    /** 时间序列ID后缀 */
    private String timeIdSuffix = "";

    private int workId = 0;

    private int dataId = 0;

}

