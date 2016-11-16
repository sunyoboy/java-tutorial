/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ScheduledExecutorTwo.java
 * Author:   DD240
 * Date:     2015-12-8 上午10:46:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.common.schedule;

import java.util.Calendar;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class ScheduledExecutorTwo extends TimerTask {
    
    private String jobName;

    /**
     * @param jobName
     */
    public ScheduledExecutorTwo(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("execute : " + jobName);
    }
    
    /**
     * 
     */
    public Calendar getEarliestDate(Calendar currentDate, int dayOfWeek, int hourOfDay, 
            int minuteOfHour, int secondOfMinute) {
        // 
        int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR);
        int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDate.get(Calendar.MINUTE);
        int currentSecond = currentDate.get(Calendar.SECOND);
        
        boolean weekLater = false;
        if(dayOfWeek < currentDayOfWeek) {
            weekLater = true;
        } else if (dayOfWeek == currentDayOfWeek) {
            // hourOfDay < currentHourOfDay
            if(hourOfDay < currentHour) {
                weekLater = true;
            } else if (hourOfDay == currentHour) {
                // 
                if(minuteOfHour < currentMinute) {
                    weekLater = true;
                } else if (minuteOfHour == currentMinute) {
                    if(secondOfMinute < currentSecond) {
                        weekLater = true;
                    }
                }
            }
        }
        
        if(weekLater) {
            currentDate.set(Calendar.WEEK_OF_YEAR, currentWeekOfYear + 1);
        }
        
        //
        currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentDate.set(Calendar.MINUTE, minuteOfHour);
        currentDate.set(Calendar.SECOND, secondOfMinute);
        return currentDate;
        
    }
    
    public static void main(String[] args) {
        ScheduledExecutorTwo two = new ScheduledExecutorTwo("job1");
        Calendar currentDate = Calendar.getInstance();
        long currentDateLong = currentDate.getTime().getTime();
        System.out.println("current Date = " + currentDate.getTime().toString());
        
        // 
        Calendar earliestDate = two.getEarliestDate(currentDate, 3, 11, 21, 10);
        
        long earliestDateLong = earliestDate.getTime().getTime();
        System.out.println("earliest Date = " + earliestDate.getTime().toString());
        
        long delay = earliestDateLong - currentDateLong;
        long period = 7 * 24 * 60 * 60 * 1000;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(two, delay, period, TimeUnit.MILLISECONDS);
        
    }
    
}
