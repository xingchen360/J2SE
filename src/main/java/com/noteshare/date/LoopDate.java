package com.noteshare.date;

import java.util.Calendar;

import org.apache.commons.lang.time.DateFormatUtils;

public class LoopDate {

	public static void main(String[] args) {
		Calendar reportCalendar = Calendar.getInstance();  
        reportCalendar.set(2015, 2, 1);  
        Calendar endCalendar = Calendar.getInstance();  
        endCalendar.set(2015, 3, 10); 
        while(reportCalendar.compareTo(endCalendar)<=0){
            String reportDate = DateFormatUtils.format(reportCalendar.getTime(), "yyyyMMdd");
            System.out.println(reportDate);
            reportCalendar.add(Calendar.DATE, 1);
        }
	}

}
