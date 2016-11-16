/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: TimerTest.java
 * Author:   DD240
 * Date:     2015-12-8 上午10:10:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.common.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class TimerTest extends TimerTask {

    private String jobNames;
    
    /**
     * @param jobNames
     */
    public TimerTest(String jobNames) {
        super();
        this.jobNames = jobNames;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("execute : " + jobNames);
    }
    
    public static void main(String[] args) {
        Timer timer = new Timer();
        long delay = 1000;
        long period = 1000;
        timer.schedule(new TimerTest("job1"), delay, period);
        
        Timer timerTwo = new Timer();
        
        long delayTwo = 2000;
        long periodTwo = 2000;
        timerTwo.schedule(new TimerTest("job2"), delayTwo, periodTwo);
        
    }
   
}
