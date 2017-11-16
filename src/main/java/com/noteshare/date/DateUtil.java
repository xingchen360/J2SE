package com.noteshare.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// 格式：年－月－日 小时：分钟：秒
	public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

	// 格式：年－月－日 小时：分钟
	public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

	// 格式：年月日
	public static final String FORMAT_THREE = "yyyyMMdd";

	// 格式：年－月－日
	public static final String FORMAT_FOUR = "yyyy-MM-dd";

	// 格式：时分秒
	public static final String FORMAT_FIVE = "HH:mm:ss";
	
	/**
     * 获取 当前年、半年、季度、月、日、小时 开始结束时间
     */

    private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    private static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @description yyyy-MM-dd 转换为 yyyyMMdd
	 * @param datestr
	 * @return
	 */
	public static String convert2yyyyMMdd(String datestr){
		return datestr.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})","$1$2$3");
	}
	/**
	 * @description yyyyMMdd 转换为 yyyy-MM-dd
	 * @param datestr
	 * @return
	 */
	public static String convert2yyyy_MM_dd(String datestr){
		return datestr.replaceAll("(\\d{4})(\\d{2})(\\d{2})","$1-$2-$3");
	}
	
	/**
     * 
     * @Description: 将字符串从一种格式转化的
     * @param @param src         日期原串
     * @param @param infmt       日期输入格式
     * @param @param outfmt      日期输出格式
     * @param @return            目标日期字符串  
     * @throws ParseException
     */
    public static String getFmtDateStr(String src,String infmt,String outfmt) throws ParseException {
        //输入格式
        DateFormat informater = new SimpleDateFormat(infmt);
        //输出格式
        DateFormat outfomater = new SimpleDateFormat(outfmt);
        String result = outfomater.format(informater.parse(src));
        return result;
    }

	/**
	 * 把符合日期格式的字符串转换为日期类型
	 * @throws ParseException 
	 */
	public static Date stringtoDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		formater.setLenient(false);
		Date d  = formater.parse(dateStr);
		return d;
	}

	/**
	 * 把日期转换为字符串
	 */
	public static String dateToString(java.util.Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String result = formater.format(date);
		return result;
	}

	/**
	 * @param dateKind
	 *            指定处理年、月、日
	 * @param dateStr
	 *            指定日期
	 * @param amount
	 *            指定长度
	 * @return
	 * @throws ParseException 
	 */
	public static String dateSub(int dateKind, String dateStr, int amount) throws ParseException {
		Date date = stringtoDate(dateStr, FORMAT_ONE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(dateKind, amount);
		return dateToString(calendar.getTime(), FORMAT_ONE);
	}

	/**
	 * 
	 * @param days
	 * @return 返回一个相加减后的日期 yyyy-MM-dd
	 */
	public static String dateSub(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return dateToString(calendar.getTime(), FORMAT_FOUR);
	}

	/**
	 * 两个日期相减
	 * 
	 * @return 相减得到的秒数
	 * @throws ParseException 
	 */
	public static long timeSub(String firstTime, String secTime) throws ParseException {
		long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
		long second = stringtoDate(secTime, FORMAT_ONE).getTime();
		return (second - first) / 1000;
	}

	/**
	 * 获取某年某月的天数 
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回日期的年
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回日期的月份，1-12
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回日期的日1-31
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 返回日期的时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR);
	}

	/**
	 * 返回日期的分
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回日期的秒
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 返回日期在一个年中是第几天
	 */
	public static int getDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 返回日期在一个月中是第几天
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回日期在一个周中是第几天
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 返回日期在一个月中是第几周
	 */
	public static int getDayOfWeekInMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 返回日期在一个年中是第几周
	 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 计算两个日期相差的天数，如果date1 > date2 返回正数，否则返回负数
	 */
	public static long dayDiff(Date date1, Date date2) {
		long diff = Math.abs(date1.getTime() - date2.getTime());
		diff /= 3600 * 1000 * 24;
		return diff;
	}

	/**
	 * 比较两个日期的年差
	 * @throws ParseException 
	 */
	public static int yearDiff(String before, String after) throws ParseException {
		Date beforeDay = stringtoDate(before, FORMAT_FOUR);
		Date afterDay = stringtoDate(after, FORMAT_FOUR);
		return getYear(afterDay) - getYear(beforeDay);
	}

	/**
	 * 比较指定日期与当前日期的差
	 * @throws ParseException 
	 */
	public static int yearDiffCurr(String after) throws ParseException {
		return yearDiff(getSysCurrentDate(), after);
	}

	/**
	 * 获取当前时间的指定格式
	 */
	public static String getCurrDate(String format) {
		return dateToString(new Date(), format);
	}
	
	/**
     * @return 当前系统时间 yyyy-MM-dd HH24:MI:SS
     */
    public static String getSysCurrentDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_ONE);
        String time = sdf.format(new java.util.Date());  
        return time;
    }
    
    /**
     * @return 当前系统时间 yyyy-MM-dd
     */
    public static String getSysCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FOUR);  
        String time = sdf.format(new java.util.Date());  
        return time;
    }
    
    /**
     * @return 当前系统时间 HH24:MI:SS
     */
    public static String getSysCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FIVE);  
        String time = sdf.format(new java.util.Date());  
        return time;
    }
    
    
    /**
     * 获得本周的第一天，周一
     * 
     * @return
     */
    public static Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的最后一天，周日
     * 
     * @return
     */
    public static Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本天的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static Date getCurrentDayStartTime() {
        Date now = new Date();
        try {
            now = shortSdf.parse(shortSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本天的结束时间，即2012-01-01 23:59:59
     * 
     * @return
     */
    public static Date getCurrentDayEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的开始时间，即2012-01-01 23:59:59
     * 
     * @return
     */
    public static Date getCurrentHourStartTime() {
        Date now = new Date();
        try {
            now = longHourSdf.parse(longHourSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的结束时间，即2012-01-01 23:59:59
     * 
     * @return
     */
    public static Date getCurrentHourEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(longHourSdf.format(now) + ":59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     * 
     * @return
     */
    public static Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     * 
     * @return
     */
    public static Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     * 
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     * 
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取前/后半年的开始时间
     * 
     * @return
     */
    public static Date getHalfYearStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;

    }

    /**
     * 获取前/后半年的结束时间
     * 
     * @return
     */
    public static Date getHalfYearEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    
    public static int getSeason(Date date){
        int season = 0;  
          
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int month = c.get(Calendar.MONTH);  
        switch (month) {  
            case Calendar.JANUARY:  
            case Calendar.FEBRUARY:  
            case Calendar.MARCH:  
                season = 1;  
                break;  
            case Calendar.APRIL:  
            case Calendar.MAY:  
            case Calendar.JUNE:  
                season = 2;  
                break;  
            case Calendar.JULY:  
            case Calendar.AUGUST:  
            case Calendar.SEPTEMBER:  
                season = 3;  
                break;  
            case Calendar.OCTOBER:  
            case Calendar.NOVEMBER:  
            case Calendar.DECEMBER:  
                season = 4;  
                break;  
            default:  
                break;  
        }  
        return season;  
    }
    /**
     * 获取上个月的第一天
     * @throws Exception
     */
    public static Date getBeforeFirstMonthdate(){
        Date start = null;
        try{
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            start = shortSdf.parse(shortSdf.format(calendar.getTime()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return start;
    }
        
    /**
     *  获取上个月的最后一天
     * @throws Exception
     */
    public static Date getBeforeLastMonthdate(){
        Date end = null;
        try{
            Calendar calendar=Calendar.getInstance();
            int month=calendar.get(Calendar.MONTH);
            calendar.set(Calendar.MONTH, month-1);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end = longSdf.parse(shortSdf.format(calendar.getTime()) + " 23:59:59"); 
        }catch (Exception e) {
            e.printStackTrace();
        }
        return end;
    }

	public static void main(String[] args) throws ParseException {
		System.out.println("当前时间：" + DateUtil.getCurrDate(FORMAT_ONE));
		System.out.println("当前时间：" + DateUtil.getSysCurrentDate());
		System.out.println("某年某月的最大天数：" + DateUtil.getDaysOfMonth(2011, 5));

		System.out.println("当前日期+2：" + DateUtil.dateSub(2));
		System.out.println("指定日期+2："
				+ DateUtil.dateSub(Calendar.DAY_OF_YEAR, "2014-01-01 00:00:00",
						2));
		System.out.println("yyyy-MM-dd转换成yyyyMMdd："
				+ DateUtil.convert2yyyyMMdd("2014-03-08"));
		System.out.println("yyyyMMdd转换成yyyy-MM-dd："
				+ DateUtil.convert2yyyy_MM_dd("20140308"));

		System.out.println("Date转换为String："
				+ DateUtil.dateToString(new Date(), FORMAT_ONE));
		System.out.println("String转换为Date："
				+ DateUtil.stringtoDate("2014-03-07 15:12:27", FORMAT_ONE));

		System.out.println("两个日期相差的天数："
				+ DateUtil.dayDiff(new Date(), DateUtil.stringtoDate(
						"2014-01-01 00:00:00", FORMAT_ONE)));

		System.out.println("返回日期的年：" + DateUtil.getYear(new Date()));// YEAR
		System.out.println("返回日期的月：" + DateUtil.getMonth(new Date()));// MONTH
		System.out.println("返回日期的日：" + DateUtil.getDay(new Date()));// DATE
		System.out.println("返回日期的时：" + DateUtil.getHour(new Date()));// HOUR
		System.out.println("返回日期的分：" + DateUtil.getMinute(new Date()));// MINUTE
		System.out.println("返回日期的秒：" + DateUtil.getSecond(new Date()));// SECOND

		System.out.println("日期在一个年中是第几天：" + DateUtil.getDayOfYear(new Date()));// WEEK_OF_YEAR
		System.out.println("日期在一个月中是第几天：" + DateUtil.getDayOfMonth(new Date()));// DAY_OF_MONTH
		System.out.println("日期在一个周中是第几天：" + DateUtil.getDayOfWeek(new Date()));// DAY_OF_WEEK
		System.out.println("日期在一个月中是第几周："
				+ DateUtil.getDayOfWeekInMonth(new Date()));// DAY_OF_WEEK_IN_MONTH
		System.out.println("返回日期在一个年中是第几周："
				+ DateUtil.getWeekOfYear(new Date()));// WEEK_OF_MONTH

		System.out.println("两个日期的年差："
				+ DateUtil.yearDiff("2013-01-01", "2014-01-01"));
		System.out
				.println("指定日期与当前日期的差：" + DateUtil.yearDiffCurr("2013-01-01"));
	}
}
