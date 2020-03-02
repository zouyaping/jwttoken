package com.qtech.jwttoken.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description: 时间处理工具类
 * @Date: 2020/1/31 15:46
 * @Version: 1.0
 */
public class TimeTuils {
    static Logger logger = LoggerFactory.getLogger(TimeTuils.class);
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 获取周几
    public static int getWeek(String timeStr){
        int day_of_week = -1;
        try {
            Date date = sdf.parse(timeStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        } catch (ParseException e) {
            logger.error("时间格式异常");
            e.printStackTrace();
        }
        return day_of_week;
    }

    // 获取上午还是下午
    public static String getMornOrAfter(String timeStr){
        String am_pm = "不详";
        try {
            Date date = sdf.parse(timeStr);
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(date);
            am_pm = gCalendar.get(GregorianCalendar.AM_PM) == Calendar.PM ? "下午" : "上午";

        } catch (ParseException e) {
            logger.error("时间格式异常");
            e.printStackTrace();
        }
        return am_pm;
    }

}
