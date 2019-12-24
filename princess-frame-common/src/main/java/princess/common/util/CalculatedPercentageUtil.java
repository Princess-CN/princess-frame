package princess.common.util;

import java.text.DecimalFormat;

/**
 * 计算百分比
 * @author liuwq
 * @date
 */
public class CalculatedPercentageUtil {
    // MathUtil

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static String format(double number) {
        return decimalFormat.format(number * 100);
    }

}
