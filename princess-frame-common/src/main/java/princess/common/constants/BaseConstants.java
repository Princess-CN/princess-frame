package princess.common.constants;

import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import princess.common.core.util.util.NetworkUtil;

/**
 * 基础常量类
 */
public class BaseConstants {

    /** ID常量 */
    public static class Ids {

        /** 未定义ID标记 */
        public static final String UNDEFINED_ID = "#";

        /** 系统内置管理员的ID */
        public static final String ADMIN_ID = "0";

        /** 根节点ID标记 */
        public static final String ROOT_ID = "0";

        /** 单位根节点ID */
        public static final String DW_ROOT_ID = "000000";
        /** 功能根节点ID */
        public static final String GN_ROOT_ID = "000000";


        /** 当前系统ID */
        public static final String CURRENT_SERVER_ID;
        static {
            final String seed;
            String[] mac = NetworkUtil.getAllMacAddress();
            if (mac != null && mac.length != 0) {
                seed = Arrays.toString(mac);
            } else {
                seed = UUID.randomUUID().toString();
            }
            CURRENT_SERVER_ID = DigestUtils.md5Hex(seed);
        }
    }

    /** 符号常量 */
    public static class Symbols {

        /** 空字符串 */
        public static final String EMPTY = "";

        /** 非标记 */
        public static final String NON = "!";

        /** 默认的路径分隔符 */
        public static final String SEPARATOR = "/";

        /** 逗号 */
        public static final String COMMA = ",";

        /** 点 */
        public static final String DOT = ".";

        /** 哈希符号 */
        public static final String HASH = "#";

        /** 密码占位符 */
        public static final String PASSWORD_PLACEHOLDER = "●●●●●●";

        /** 重定向前缀 */
        public static final String REDIRECT_PREFIX = "redirect:";

        /** 令牌名 */
        public static final String TOKEN = "token";
    }

    /** 返回结果枚举 */
    public static enum ResultStatus {
        OK(0), ERROR(1);
        public final Integer VALUE;

        private ResultStatus(Integer value) {
            this.VALUE = value;
        }
    }

    /** 布尔数值常量 */
    public static class Bool {

        /** 真 */
        public static final String Y = "Y";
        /** 假 */
        public static final String N = "N";

        /**
         * 校验有效性
         * @param v 布尔数值
         * @return 如果不是布尔数值 (Y或 N)返回false
         */
        public static boolean check(String v) {
            return Y.equals(v) || N.equals(v);
        }

        /**
         * 规范化布尔数值
         * @param v 布尔数值
         * @return 布尔数值(Y或 N)
         */
        public static String normalize(String v) {
            return normalize(v, N);
        }

        /**
         * 规范化布尔数值
         * @param v 布尔数值
         * @param d 默认值
         * @return 布尔数值(Y或 N)
         */
        public static String normalize(String v, String d) {
            return StringUtils.isEmpty(d) ? null : Y.equals(v) ? Y : N;
        }
    }

    /** 功能权限类型 */
    public static class PermissionType {
        /** 模块 */
        public static final Integer MODULE = 1;
        /** 菜单 */
        public static final Integer MENU = 2;
        /** 按钮 */
        public static final Integer BUTTON = 3;
    }
}
