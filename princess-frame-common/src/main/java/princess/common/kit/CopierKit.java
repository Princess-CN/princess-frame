package princess.common.kit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;

/**
 * 用于复制对象的工具类
 */
public class CopierKit {

    private static final Map<String, Map<String, BeanCopier>> COPIER_CACHES;
    static {
        COPIER_CACHES = new ConcurrentHashMap<String, Map<String, BeanCopier>>();
    }

    /**
     * 拷贝对象属性
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        Class<?> sourceType = source.getClass();
        Class<?> targetType = target.getClass();
        BeanCopier copier = getCopier(sourceType, targetType);
        copier.copy(source, target, null);
    }

    /**
     * 获得Bean属性拷贝器
     * @param sourceType 源对象类型
     * @param targetType 目标对象类型
     * @return Bean属性拷贝器
     */
    public static BeanCopier getCopier(Class<?> sourceType, Class<?> targetType) {
        String sourceTypeName = sourceType.getName();
        String targetTypeName = targetType.getName();
        Map<String, BeanCopier> copiers = COPIER_CACHES.get(sourceTypeName);
        if (copiers == null) {
            synchronized (COPIER_CACHES) {
                copiers = COPIER_CACHES.get(sourceTypeName);
                if (copiers == null) {
                    copiers = new ConcurrentHashMap<String, BeanCopier>();
                    COPIER_CACHES.put(sourceTypeName, copiers);
                }
            }
        }
        BeanCopier copier = copiers.get(targetTypeName);
        if (copier == null) {
            synchronized (copiers) {
                copier = copiers.get(targetTypeName);
                if (copier == null) {
                    copier = BeanCopier.create(sourceType, targetType, false);
                    copiers.put(targetTypeName, copier);
                }
            }
        }
        return copier;
    }

}
