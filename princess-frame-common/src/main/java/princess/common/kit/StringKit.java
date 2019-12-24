package princess.common.kit;

import base.lang.StringUtil;

public class StringKit {

    private static final String SEPARATOR = ",";

    /**
     * 切分字符串，分隔符字符为","，会自动排除掉空的字符串.
     * @param cs 要处理的字符串
     * @return 切分后的字符串数组
     */
    public static String[] splitPurify(final CharSequence cs) {
        return StringUtil.splitPurify(cs, SEPARATOR);
    }
}
