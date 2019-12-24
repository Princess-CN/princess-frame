package base.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateUtil {

    /** 0001-01-01T00:00:00 */
    public static final Long MIN_MILLIS = -62135798400000L;
    /** 9999-12-31T23:59:59 */
    public static final Long MAX_MILLIS = 253402271999000L;
    /** 0001-01-01T00:00:00 */
    public static final Date MIN_DATE = new Date(MIN_MILLIS);
    /** 9999-12-31T23:59:59 */
    public static final Date MAX_DATE = new Date(MAX_MILLIS);

    /** ISO日期格式 */
    public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /** 时间单位 */
    public static enum DateUnit {
        /** 年 */
        YEAR,
        /** 月 */
        MONTH,
        /** 日 */
        DATE,
        /** 时 */
        HOUR_OF_DAY,
        /** 分 */
        MINUTE,
        /** 秒 */
        SECOND
    }
    
    /**
     * 获取月初时间，下月初
     * @param date
     * @return
     */
    public static String getYueDateStrFormat(Date date, Boolean isNow, String formatStr ){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date nowTime = null;
        nowTime = calendar.getTime();
        if(isNow){

        }else{
            calendar.add(Calendar.MONTH, 1);
            nowTime = calendar.getTime();
        }
        SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
        return sdf.format(nowTime);
    }

    /** 日期格式化类线程变量(保证线程安全) */
    private static final ThreadLocal<DateFormat> ISO_DATEFORMAT_HOLDER = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(ISO_DATETIME_FORMAT);
        };
    };

    /**
     * 解析日期字符串
     * @param value 日期字符串
     * @return 日期对象
     */
    public static Date parse(String value) {
        if (value == null) {
            return null;
        }
        try {
            return forceParseDate(value, Locale.ENGLISH);
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    /**
     * 日期格式化
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String format(Date date) {
        return date != null ? ISO_DATEFORMAT_HOLDER.get().format(date) : null;
    }

    /**
     * 获得该时间范围开始的时间点
     * @param date 日期
     * @param unit 时间单位
     * @return 最后一个时间点
     */
    public static Date begin(Date date, DateUnit unit) {
        if (date == null) {
            return null;
        }
        Calendar calendar = toCalendar(date);
        switch (unit) {
            case YEAR:
                calendar.set(Calendar.MONTH, Calendar.JANUARY);
            case MONTH:
                calendar.set(Calendar.DATE, 1);
            case DATE:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
            case HOUR_OF_DAY:
                calendar.set(Calendar.MINUTE, 0);
            case MINUTE:
                calendar.set(Calendar.SECOND, 0);
            default:
                calendar.set(Calendar.MILLISECOND, 0);
        }
        return calendar.getTime();
    }

    /**
     * 获得该时间范围最后一个时间点
     * @param date 日期
     * @param unit 时间单位
     * @return 最后一个时间点
     */
    public static Date end(Date date, DateUnit unit) {
        if (date == null) {
            return null;
        }
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.MILLISECOND, 999);
        switch (unit) {
            case YEAR:
                calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            case MONTH:
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
            case DATE:
                calendar.set(Calendar.HOUR_OF_DAY, 23);
            case HOUR_OF_DAY:
                calendar.set(Calendar.MINUTE, 59);
            case MINUTE:
                calendar.set(Calendar.SECOND, 59);
            default:
                calendar.set(Calendar.MILLISECOND, 999);
        }
        return calendar.getTime();
    }

    /**
     * 获得当前日期
     * @return 当前日期
     */
    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获得 Unix时间戳 (格林威治时间1970年01月01日00时00分00秒起至现在的总秒数)
     * @param date 时间
     * @return Unix时间戳
     */
    public static long getUnixTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 根据Unix时间戳获得日期
     * @param seconds Unix时间戳(相对于1970年的秒数)
     * @return 日期对象
     */
    public static Date ofUnixTimestamp(long seconds) {
        return new Date(seconds * 1000L);
    }

    /**
     * 转换Date对象为Calendar对象
     * @param date Date对象
     * @return Calendar对象
     */
    private static Calendar toCalendar(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 解析日期字符串
     * @param value 日期字符串
     * @param locale 地区对象
     * @return 日期对象
     */
    private static Date forceParseDate(final String str, final Locale locale) throws ParseException {

        if (str == null) {
            throw new IllegalArgumentException("DateString must not be null");
        }

        SimpleDateFormat parser;
        if (locale == null) {
            parser = new SimpleDateFormat();
        } else {
            parser = new SimpleDateFormat("", locale);
        }

        // 不严格模式
        parser.setLenient(true);
        final ParsePosition pos = new ParsePosition(0);
        for (final String parsePattern : PARSE_DATE_PATTERNS) {

            String pattern = parsePattern;

            // LANG-530 - need to make sure 'ZZ' output doesn't get passed to SimpleDateFormat
            if (parsePattern.endsWith("ZZ")) {
                pattern = pattern.substring(0, pattern.length() - 1);
            }

            parser.applyPattern(pattern);
            pos.setIndex(0);

            String str2 = str;
            // LANG-530 - need to make sure 'ZZ' output doesn't hit SimpleDateFormat as it will
            // ParseException
            if (parsePattern.endsWith("ZZ")) {
                str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
            }

            final Date date = parser.parse(str2, pos);
            if (date != null && pos.getIndex() == str2.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    /** 常用的日期格式 */
    private final static String[] PARSE_DATE_PATTERNS = { //
            ISO_DATETIME_FORMAT, //
            "yyyy-MM-dd HH:mm:ss", //
            "yyyy-MM-dd HH:mm", //
            "yyyy-MM-dd HH", //
            "yyyy-MM-dd", //
            "yyyy-MM", //
            "yyyyMMddHHmmss", //
            "yyyyMMdd", //
            "yyyyMM", //
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", //
            "yyyy-MM-dd'T'HH:mm:ss.SSS", //
            "yyyy-MM-dd HH:mm:ss.SSS", //
            "yyyy-MM-dd'T'HH:mm:ss'Z'", //
            "EEE MMM dd HH:mm:ss zzz yyyy", //
            "yyyy/MM/dd HH:mm:ss", //
            "yyyy-MM-dd'T'HH:mm", //
            "yyyy-MM-dd HH:mm 'BJT'", //
            "d MMM yyyy h:m a", //
            "MMM d, yyyy HH:mm", //
            "MMM d, yyyy", //
            "yyyy/MM/dd", //
            "MM/dd/yyyy", //
            "MMM d yyyy"//
    };


}
