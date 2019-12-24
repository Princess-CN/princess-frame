package huacloud.jwdsj.common.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import base.identifier.TimeIdWorker;
import huacloud.jwdsj.common.component.enums.IdGroup;
import huacloud.jwdsj.common.configuration.CustomProperties;

/**
 * 序列ID生成器<br>
 * 格式为 日期(年月日时分秒)+秒内计数(9999)+后缀(机器号) <br>
 */
@Component
public class IdComponent {

    // ==============================Fields===========================================
    private final Map<IdGroup, TimeIdWorker> timeIdWorkers = new ConcurrentHashMap<>();

    @Autowired
    private CustomProperties properties;

    private String timeIdSuffix;

    // ==============================Methods==========================================
    /**
     * 下一个ID
     * @param group ID组
     * @return ID
     */
    public String nextId(IdGroup group) {
        return getTimeIdWorker(group).nextId();
    }

    /**
     * 下一个ID
     * @return ID
     */
    public String nextId() {
        return nextId(IdGroup.DEFAULT);
    }

    /**
     * 获得日期时间序列ID生成器
     * @param group ID组
     * @return ID生成器
     */
    protected TimeIdWorker getTimeIdWorker(IdGroup group) {
        TimeIdWorker idWorker = timeIdWorkers.get(group);
        if (idWorker == null) {
            synchronized (group) {
                idWorker = new TimeIdWorker(group.prefix, timeIdSuffix);
                timeIdWorkers.put(group, idWorker);
            }
        }
        return idWorker;
    }

    // ==============================OverrideMethods==================================
    @PostConstruct
    public void afterPropertiesSet() {
        timeIdSuffix = properties.getTimeIdSuffix();
    }
}
