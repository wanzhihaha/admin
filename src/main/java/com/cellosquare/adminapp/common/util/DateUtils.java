package main.java.com.cellosquare.adminapp.common.util;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * compareDate
     * (日期比较，如果s>=e 返回true 否则返回false)
     *
     * @param s
     * @param e
     * @return boolean
     */
    public static boolean compareDate(String s, String e) {
        if (parseTime(s) == null || parseTime(e) == null) {
            return false;
        }
        return parseTime(s).getTime() >= parseTime(e).getTime();
    }

    public static boolean compareDate(Timestamp s, String e) {
        if (s == null || parseTime(e) == null) {
            return false;
        }
        return s.getTime() >= parseTime(e).getTime();
    }
    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String parseTime(Timestamp date, String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(date.getTime()));
    }
    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 起始时间
     * @param i
     * @param date
     * @return
     */
    public static Date getDateForMatt(Integer i,String date) {
        return i==1? parse(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss"):parse(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        String ss=  "lll";

        System.out.println(SensitiveWordBs.newInstance().findAll(ss));
    }
}
