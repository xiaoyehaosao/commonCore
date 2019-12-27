package com.xyhs.common.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ljp
 * @apiNote
 * @date 14:15 2019/12/27
 **/
public class DateTimeUtils extends DateUtils {

    private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyyMMdd", "yyyyMMddHHmmss", "yyyyMMddHHmm", "yyyyMM"};

    public DateTimeUtils() {
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    public static String getDate(String pattern) {
        Calendar calendar = Calendar.getInstance();
        return DateFormatUtils.format(calendar.getTime(), pattern);
    }

    public static Date getSMDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getSMDate(int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(type, value);
        return calendar.getTime();
    }

    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }

        return formatDate;
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.getTime(), "HH:mm:ss");
    }

    public static String getDateTime() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getYear() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.getTime(), "yyyy");
    }

    public static String getMonth() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.getTime(), "MM");
    }

    public static String getDay() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.getTime(), "dd");
    }

    public static String getWeek() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.getTime(), "E");
    }

    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        } else {
            try {
                return parseDate(str.toString(), parsePatterns);
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    public static long pastDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        long t = calendar.getTime().getTime() - date.getTime();
        return t / 86400000L;
    }

    public static long pastHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        long t = calendar.getTime().getTime() - date.getTime();
        return t / 3600000L;
    }

    public static long pastMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        long t = calendar.getTime().getTime() - date.getTime();
        return t / 60000L;
    }

    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / 86400000L;
        long hour = timeMillis / 3600000L - day * 24L;
        long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
        long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        long sss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
        return (day > 0L ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (double)((afterTime - beforeTime) / 86400000L);
    }

    public static String StringToDate(String dateCS) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(dateCS)) {
            try {
                Date date = sdf.parse(dateCS);
                dateCS = sdf1.format(date);
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
        }

        return dateCS;
    }

    private static List<Date> getAllTheDateOftheMonth(Date date) {
        List<Date> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        int month = cal.get(2);

        while(cal.get(2) == month) {
            list.add(cal.getTime());
            cal.add(5, 1);
        }

        return list;
    }

    public static long[] getDistanceTimes(Date date1, Date date2) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0L;
        long hour = 0L;
        long min = 0L;
        long sec = 0L;

        try {
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            day = diff / 86400000L;
            hour = diff / 3600000L - day * 24L;
            min = diff / 60000L - day * 24L * 60L - hour * 60L;
            sec = diff / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        long[] times = new long[]{day, hour, min, sec};
        return times;
    }

    public static String getDistanceTime(Date date1, Date date2) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0L;
        long hour = 0L;
        long min = 0L;
        long sec = 0L;

        try {
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            day = diff / 86400000L;
            hour = diff / 3600000L - day * 24L;
            min = diff / 60000L - day * 24L * 60L - hour * 60L;
            sec = diff / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    public static Long getDistanceTimeLong(Date date1, Date date2) {
        long diff = 0L;

        try {
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return diff;
    }

    public static Boolean after(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar1.after(calendar2) ? true : false;
    }

    public static Boolean before(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar1.before(calendar2) ? true : false;
    }

    public static int dayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(7);
    }

    public static Date getWeekDay(int dayofweek) throws ParseException {
        Date day = null;
        if (dayofweek > 0 && dayofweek < 8) {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(2);
            calendar.set(7, dayofweek);
            day = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(day);
            day = simpleDateFormat.parse(format);
        }

        return day;
    }

    public static String getWeekDay(int dayofweek, Object... pattern) {
        if (dayofweek > 0 && dayofweek < 8) {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(2);
            calendar.set(7, dayofweek);
            Date day = calendar.getTime();
            return formatDate(day, pattern);
        } else {
            return "";
        }
    }

}
