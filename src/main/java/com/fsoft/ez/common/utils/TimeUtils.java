package com.fsoft.ez.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

public final class TimeUtils {

    /** タイムスタンプの表示形式 */
    private static final String FORMAT_TIMESTAMP = "yyyy/MM/dd HH:mm";

    private static final String TIME_ZONE_ID = "Asia/Tokyo";

    /** Privateコンストラクタ */
    private TimeUtils() {

    }

    /**
     * 表示形式
     *
     * @param date 日付
     * @return 日付の表示形式
     */
    public static String formatDate(Date date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_TIMESTAMP);
        formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_ID));
        return formatter.format(date);
    }

    public static String formatISODateTime(LocalDate date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return date.format(formatter);
    }

    public static final LocalDate toLocalDate(String localDateStr, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(localDateStr, formatter);
    }
}
