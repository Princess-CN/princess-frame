package princess.common.kit;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import princess.common.base.model.DateBounds;

/**
 * 日期时间相关工具
 */
public class DateKit {

    /**
     * 计算年份同比区间
     * @param bounds 时间区间
     * @return 前一个同比的时间区间
     */
    public static DateBounds getPreY2Y(DateBounds bounds) {
        return getPreY2Y(bounds.getBegin(), bounds.getEnd());
    }

    /**
     * 计算年份同比区间
     * @param begin 开始时间
     * @param end 结束时间
     * @return 前一个同比的时间区间
     */
    public static DateBounds getPreY2Y(Date begin, Date end) {

        Calendar beginCalendar = DateUtils.toCalendar(begin);
        Calendar endCalendar = DateUtils.toCalendar(end);

        // 年份差
        int yearDifference = endCalendar.get(Calendar.YEAR) - beginCalendar.get(Calendar.YEAR);

        // 年份偏移 [-(1+yearDifference)]
        int yearAmount = Math.negateExact(Math.abs(1 + yearDifference));

        // 根据年份偏移计算前一个同比区间
        beginCalendar.add(Calendar.YEAR, -1 - yearAmount);
        endCalendar.add(Calendar.YEAR, -1 - yearAmount);

        Date preBegin = beginCalendar.getTime();
        Date preEnd = endCalendar.getTime();

        DateBounds preBounds = new DateBounds(preBegin, preEnd);

        return preBounds;
    }
}
