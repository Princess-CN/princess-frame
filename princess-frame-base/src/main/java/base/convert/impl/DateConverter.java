package base.convert.impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.convert.Converter;
import base.date.DateUtil;


/**
 * 日期类型转换器
 * @author YYL
 * @version 2012-12-11
 * @see Converter
 */
public class DateConverter implements Converter<Date> {

    public static final DateConverter INSTANCE = new DateConverter();

    private static final Pattern DATE_PATTERN = Pattern.compile("^new Date\\((\\d+)\\)$");
    private static final Pattern DIGITS_PATTERN = Pattern.compile("^-?\\d+$");

    public Date convert(Object source, Class<? extends Date> toType, Date vDefault) {
        try {
            if (source == null) {
                return vDefault;
            }
            if (source instanceof Date) {
                return new Date(((Date) source).getTime());
            }
            if (source instanceof String) {
                String value = (String) source;
                Matcher digitsMatcher = DIGITS_PATTERN.matcher(value);
                if (digitsMatcher.matches()) {
                    return new java.util.Date(Long.parseLong(value));
                }
                Matcher dateMatcher = DATE_PATTERN.matcher(value);
                if (dateMatcher.matches()) {
                    if (dateMatcher.find()) {
                        String msel = dateMatcher.group(1);
                        return new java.util.Date(Long.parseLong(msel));
                    }
                }
                return DateUtil.parse((String) source);
            }
        } catch (Exception e) {
            // Ignore//
        }
        return vDefault;
    }

    @Override
    public boolean support(Class<? extends Date> type) {
        return Date.class.isAssignableFrom(type);
    }
}
