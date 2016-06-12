package com.example.chenlongjian.ycf_customview.common;

import android.text.TextUtils;

import com.example.chenlongjian.ycf_customview.model.MonthCellDescriptor;
import com.example.chenlongjian.ycf_customview.model.MonthLimitWrap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

/**
 * Created by chenlongjian on 2016/6/12.
 */
public class DataUtil {
    public static final String Y4M2D2 = "yyyy-MM-dd";
    public static final String YMDMS = "yyyyMMddHHmmss";
    public static final String FORMATPATTERN3 = "yyyy-MM-dd HH:mm:ss";
    public static final String M2D2 = "MM-dd";
    public static final String FORMATPATTERN2 = "yyyy-MM-dd HH:mm";

    public static final Locale LOCALE = Locale.CHINA;

    /**
     * 计算两个日期相差的天数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return
     */
    public static int getIntervalDay(Calendar start, Calendar end) {
        double daycount = Math.ceil((end.getTimeInMillis() - start
                .getTimeInMillis()) * 1.0 / (1000 * 3600 * 24));
        return (int) daycount + 1;
    }

    public static Date parse2date(final String pattern, final String datestr)
            throws ParseException {
        SimpleDateFormat datafm = new SimpleDateFormat(pattern, LOCALE);
        return datafm.parse(datestr);
    }

    public static Calendar parse2calendar(final String pattern,
                                          final String date_str) {

        SimpleDateFormat datafm = new SimpleDateFormat(pattern, LOCALE);
        Calendar result = getCalendarInstance();
        try {
            if (!TextUtils.isEmpty(date_str)) {
                result.setTime(datafm.parse(date_str));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Calendar getCalendarInstance() {
        Calendar c = Calendar.getInstance(LOCALE);
        setMidnight(c);
        return c;
    }

    public static Calendar getCalendarInstance(Calendar clone) {
        Calendar c;
        c = Calendar.getInstance(LOCALE);
        setMidnight(c);
        c.setTime(clone.getTime());
        return c;
    }

    public static Calendar getCalendarInstance(Date clone) {
        Calendar c = Calendar.getInstance(LOCALE);
        c.setTime(clone);
        setMidnight(c);
        return c;
    }

    public static Calendar addMothsLater(final Calendar cal,
                                         final int monthCount) {
        Calendar c = Calendar.getInstance(LOCALE);
        c.setTime(cal.getTime());
        c.add(Calendar.MONTH, monthCount);
        return c;
    }

    /**
     * 将参数日期加n，提取字符串
     *
     * @param targetcal
     * @return
     */
    public static String addNDaytoStr(final String pattern, Calendar targetcal,
                                      int n) {
        Calendar nc = getCalendarInstance(targetcal);
        nc.add(Calendar.DAY_OF_YEAR, n);
        SimpleDateFormat datafm = new SimpleDateFormat(pattern, Locale.CHINESE);
        return datafm.format(nc.getTime());
    }

    /**
     * @param
     * @return
     * @throws
     * @Title: formateDate2Str
     * @Description: 格式化日期输出
     */
    public static String formateDate2Str(String parserPattern,
                                         String targetPattern, String datestr) {

        if (datestr == null) {
            return "";
        }

        SimpleDateFormat datefm = new SimpleDateFormat(targetPattern,
                Locale.CHINESE);
        Date tdate = null;
        try {
            tdate = parse2date(parserPattern, datestr);
        } catch (ParseException e) {
            return datestr;
        }

        return datefm.format(tdate);

    }

    public static String getCalendarString(final String pattern,
                                           final Calendar cldar) {
        SimpleDateFormat datefm = new SimpleDateFormat(pattern, LOCALE);
        return datefm.format(cldar.getTime());
    }

    public static String getDateString(final String pattern, final Date date) {
        SimpleDateFormat datefm = new SimpleDateFormat(pattern, LOCALE);
        return datefm.format(date);
    }


    public static void setMidnight(Calendar cal) {
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
    }

    public static String formatMonthDate(Date date) {
        SimpleDateFormat datefm = new SimpleDateFormat("MM月dd日",
                Locale.CHINESE);
        String str = datefm.format(date);
        return str;
    }


    /**
     * @param
     * @return
     * @throws
     * @Title: getMonthCount
     * @Description: 获得隐藏的行信息
     */
    public static MonthLimitWrap getMonthCount(Calendar startCal, int weekLine) {

        MonthLimitWrap warp = new MonthLimitWrap();

        Calendar c = DataUtil.getCalendarInstance();
        c.setTime(startCal.getTime());

        final int maxofmonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        final int updayofweek = c.get(Calendar.DAY_OF_WEEK);

        int offsetday = maxofmonth - c.get(Calendar.DAY_OF_MONTH) + 1;
        offsetday += updayofweek - 1;
        c.set(Calendar.DAY_OF_MONTH, maxofmonth);
        offsetday += 7 - c.get(Calendar.DAY_OF_WEEK);

        // 上限&下限

        Calendar uplimit = Calendar.getInstance(Locale.CHINA);
        uplimit.setTime(startCal.getTime());
        uplimit.add(Calendar.DAY_OF_YEAR, -(updayofweek - 1));

        Calendar lowlimit = Calendar.getInstance(Locale.CHINA);
        lowlimit.setTime(startCal.getTime());

        int addoffsetday = 0;

        if (offsetday >= weekLine * 7) {
            // 一个月
            // return 1;
            addoffsetday = weekLine * 7 - updayofweek;
            warp.setMonthcount(1);

            Calendar ctime = DataUtil.getCalendarInstance(startCal);
            ctime.set(Calendar.DAY_OF_MONTH, 1);

            int curday = uplimit.get(Calendar.DAY_OF_MONTH);
            int max = ctime.getActualMaximum(Calendar.DAY_OF_MONTH);
            int sum;
            if (uplimit.get(Calendar.MONTH) != ctime.get(Calendar.MONTH)) {
                sum = 1 + addoffsetday + updayofweek
                        - ctime.get(Calendar.DAY_OF_WEEK) - 1 - 2;
            } else {
                sum = curday + addoffsetday + updayofweek - 2;
            }

            if (sum > max) {
                addoffsetday = addoffsetday - (sum - max) - 1;
            }
        } else {
            // 两个月
            // return 2;

            Calendar ctime = DataUtil.getCalendarInstance(startCal);
            ctime.set(Calendar.DAY_OF_MONTH,
                    ctime.getActualMaximum(Calendar.DAY_OF_MONTH));

            if (ctime.get(Calendar.DAY_OF_WEEK) == 7) {
                addoffsetday = weekLine * 7 - updayofweek;
            } else {
                addoffsetday = weekLine * 7 - updayofweek - 7;
            }

            warp.setMonthcount(2);
        }

        lowlimit.add(Calendar.DAY_OF_YEAR, addoffsetday);

        warp.setUplimitDate(uplimit);
        warp.setLowlimitDate(lowlimit);

        return warp;

    }

    /**
     * @param
     * @return
     * @throws
     * @Title: searchMonthCell
     * @Description: 将月份数据中指定日期的实体对象返回
     */
    public static MonthCellDescriptor searchMonthCell(
            List<List<MonthCellDescriptor>> mcells, Calendar target) {

        Calendar scdate = DataUtil.getCalendarInstance();
        scdate.setTime(target.getTime());
        Calendar cpscdate = DataUtil.getCalendarInstance();
        cpscdate.setTime(target.getTime());
        cpscdate.set(Calendar.DAY_OF_MONTH, 1);
        int sum = scdate.get(Calendar.DAY_OF_MONTH)
                + cpscdate.get(Calendar.DAY_OF_WEEK) - 1;
        int line = 0;
        if (sum % 7 == 0) {
            line = sum / 7;
        } else {
            line = sum / 7 + 1;
        }
        List<MonthCellDescriptor> weekcell = mcells.get(line - 1);
        MonthCellDescriptor targetcell = weekcell.get(scdate
                .get(Calendar.DAY_OF_WEEK) - 1);
        return targetcell;
    }

    /**
     * 计算相差时间
     *
     * @return 返回秒
     */
    public static long subTime(Calendar c1, Calendar c2) {
        long time_a = c1.getTime().getTime();
        long time_b = c2.getTime().getTime();
        long result = time_b - time_a;
        if (result > 0) {
            return result / 1000L;
        } else {
            return 0;
        }
    }


    /**
     * 计算两个日期相差的天数
     *
     * @return
     */
    public static int subTimeOfDay(long timeone, long timetwo) {
        Date d1 = new Date(timeone);
        Date d2 = new Date(timetwo);
        d1.setHours(0);
        d1.setMinutes(0);
        d1.setSeconds(0);
        d2.setHours(0);
        d2.setMinutes(0);
        d2.setSeconds(0);
        long s1 = d1.getTime();
        long s2 = d2.getTime();
        int result = (int) ((s2 / 86400000L) - (s1 / 86400000L));
        if (result < 0) {
            result = 0;
        }
        return result;
    }


    /**
     * @param firstCalendar
     * @param secondCalendar
     * @return 比较两个日期的先后顺序
     */
    public static int compareTwoCalendar(Calendar firstCalendar, Calendar secondCalendar) {
        setMidnight(firstCalendar);
        setMidnight(secondCalendar);
        return firstCalendar.compareTo(secondCalendar);
    }

    public static int calculateDayOffset(Calendar firstCalendar, Calendar secondCalendar) {
        int firstDay = firstCalendar.get(Calendar.DAY_OF_YEAR);
        int secondDay = secondCalendar.get(Calendar.DAY_OF_YEAR);
        return secondDay - firstDay;
    }

}
