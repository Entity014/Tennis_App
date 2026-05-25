package cn.qqtheme.framework.util;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public class DateUtils extends android.text.format.DateUtils {

    public enum DifferenceMode {
        Second,
        Minute,
        Hour,
        Day
    }

    public static long calculateDifferentSecond(Date date, Date date2) {
        return calculateDifference(date, date2, DifferenceMode.Second);
    }

    public static long calculateDifferentMinute(Date date, Date date2) {
        return calculateDifference(date, date2, DifferenceMode.Minute);
    }

    public static long calculateDifferentHour(Date date, Date date2) {
        return calculateDifference(date, date2, DifferenceMode.Hour);
    }

    public static long calculateDifferentDay(Date date, Date date2) {
        return calculateDifference(date, date2, DifferenceMode.Day);
    }

    public static long calculateDifferentSecond(long j, long j2) {
        return calculateDifference(j, j2, DifferenceMode.Second);
    }

    public static long calculateDifferentMinute(long j, long j2) {
        return calculateDifference(j, j2, DifferenceMode.Minute);
    }

    public static long calculateDifferentHour(long j, long j2) {
        return calculateDifference(j, j2, DifferenceMode.Hour);
    }

    public static long calculateDifferentDay(long j, long j2) {
        return calculateDifference(j, j2, DifferenceMode.Day);
    }

    public static long calculateDifference(long j, long j2, DifferenceMode differenceMode) {
        return calculateDifference(new Date(j), new Date(j2), differenceMode);
    }

    public static long calculateDifference(Date date, Date date2, DifferenceMode differenceMode) {
        long[] calculateDifference = calculateDifference(date, date2);
        if (differenceMode.equals(DifferenceMode.Minute)) {
            return calculateDifference[2];
        }
        if (differenceMode.equals(DifferenceMode.Hour)) {
            return calculateDifference[1];
        }
        if (differenceMode.equals(DifferenceMode.Day)) {
            return calculateDifference[0];
        }
        return calculateDifference[3];
    }

    public static long[] calculateDifference(Date date, Date date2) {
        return calculateDifference(date2.getTime() - date.getTime());
    }

    public static long[] calculateDifference(long j) {
        long j2 = j / 86400000;
        long j3 = j % 86400000;
        long j4 = j3 / 3600000;
        long j5 = j3 % 3600000;
        long j6 = j5 / 60000;
        long j7 = j5 % 60000;
        long j8 = j7 / 1000;
        LogUtils.debug(String.format("different: %d ms, %d days, %d hours, %d minutes, %d seconds", Long.valueOf(j7), Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j8)));
        return new long[]{j2, j4, j6, j8};
    }

    public static int calculateDaysInMonth(int i) {
        return calculateDaysInMonth(0, i);
    }

    public static int calculateDaysInMonth(int i, int i2) {
        List asList = Arrays.asList(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "3", "5", "7", "8", "10", "12");
        List asList2 = Arrays.asList("4", "6", "9", "11");
        if (asList.contains(String.valueOf(i2))) {
            return 31;
        }
        if (asList2.contains(String.valueOf(i2))) {
            return 30;
        }
        if (i <= 0) {
            return 29;
        }
        return ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) ? 28 : 29;
    }

    public static String fillZero(int i) {
        StringBuilder sb = i < 10 ? new StringBuilder("0") : new StringBuilder("");
        sb.append(i);
        return sb.toString();
    }

    public static boolean isSameDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static Date parseDate(String str, String str2) {
        try {
            return new Date(new SimpleDateFormat(str2).parse(str).getTime());
        } catch (Exception e) {
            LogUtils.warn(e);
            return null;
        }
    }

    public static Date parseDate(String str) {
        return parseDate(str, "yyyy-MM-dd HH:mm:ss");
    }
}
