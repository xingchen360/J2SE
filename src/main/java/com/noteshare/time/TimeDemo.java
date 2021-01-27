package com.noteshare.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * @title: TimeDemo
 * @description: LocalDate、LocalTime、LocalDateTime
 * 和SimpleDateFormat相比，DateTimeFormatter是线程安全的
 * @author: haixin
 * @date: 2021/1/27
 * @version: V1.0
 */
public class TimeDemo {
    public static void main(String[] args) {
        // 基础测试
        //test1();
        //localDate();
        localTime();
        //localDateTime();
    }

    private static void localDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        System.out.println("localDateTime:" + localDateTime);
        System.out.println("localDateTime1:" + localDateTime1);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        System.out.println("localDateTime2:" + localDateTime2);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        System.out.println("localDateTime3:" + localDateTime3);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        System.out.println("localDateTime4:" + localDateTime4);
        System.out.println("localDateTime====:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private static void localTime() {
        LocalTime localTime = LocalTime.of(13, 51, 10);
        LocalTime localTime1 = LocalTime.now();
        System.out.println("当前时间：" + localTime);
        System.out.println("当前时间：" + localTime1);
        //获取时分秒
        //获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        System.out.println("当前小时：" + hour);
        System.out.println("当前小时：" + hour1);
        //获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println("当前分钟：" + minute);
        System.out.println("当前分钟：" + minute1);
        //获取秒
        int second = localTime.getMinute();
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("当前秒：" + second);
        System.out.println("当前秒：" + second1);
        System.out.println("================================");
        localTime.atDate(LocalDate.now());
        System.out.println(localTime);
        System.out.println(localTime.getNano());
    }

    /**
     * java8全新的日期和时间API
     */
    private static void localDate() {
        System.out.println("===================localDate===========================");
        //获取当前年月日
        LocalDate localDate = LocalDate.now();
        System.out.println("当前的年月日:" + localDate);
        //构造指定的年月日
        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        System.out.println("指定的年月日:" + localDate1);
        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        System.out.println("当前年:" + year);
        System.out.println("当前年:" + year1);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("当前月:" + month);
        System.out.println("当前月:" + month1);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("当前天:" + day);
        System.out.println("当前天:" + day1);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("当前星期:" + dayOfWeek);
        System.out.println("当前星期:" + dayOfWeek1);
        System.out.println("是否是闰年：" + localDate.isLeapYear());
    }


    private static void test1() {
        System.out.println("===================test1===========================");
        Date date = new Date();
        System.out.println("=========date==========");
        System.out.println(date);
        System.out.println(date.getYear());
        System.out.println(date.getDay());
        System.out.println("=============System.currentTimeMillis():============");
        //获得系统的时间，单位为毫秒,转换为妙
        long totalMilliSeconds = System.currentTimeMillis();
        long totalSeconds = totalMilliSeconds / 1000;
        //求出现在的秒
        long currentSecond = totalSeconds % 60;
        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;
        //求出现在的小时
        long totalHour = totalMinutes / 60;
        long currentHour = totalHour % 24;
        // 求天
        long totalDay = totalHour / 24;
        long currentDay = totalHour % 24;
        //显示时间
        System.out.println("总毫秒为： " + totalMilliSeconds);
        System.out.println(currentDay + " " + currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");
        Calendar cal = Calendar.getInstance();
        System.out.println("=============Calender:=============");
        System.out.println(cal.getTimeInMillis());
        System.out.println(cal.getTimeZone());
        System.out.println("=======SimpleDateFormat======");
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        sdf.applyPattern("yyyy-MM-dd HHH:mm:ss");
        System.out.println(sdf.format(date));
    }
}
