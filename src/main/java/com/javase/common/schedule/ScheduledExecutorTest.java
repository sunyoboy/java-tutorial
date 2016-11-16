/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ScheduledExecutorTest.java
 * Author: DD240
 * Date: 2015-12-8 上午10:36:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author> sunlj <time> <version> <desc>
 * 修改人姓名 修改日期 版本号 描述
 */
package com.javase.common.schedule;

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
public class ScheduledExecutorTest implements Runnable {

    private String jobName;

    /**
     * @param jobName
     */
    public ScheduledExecutorTest(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("execute : " + jobName);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay = 1;
        long period = 1;
        service.scheduleAtFixedRate(new ScheduledExecutorTest("jobOne"),
                initialDelay, period, TimeUnit.SECONDS);
        
        long initialDelayTwo = 2;
        long periodTwo = 2;
        service.scheduleWithFixedDelay(new ScheduledExecutorTest("jobTwo"),
                initialDelayTwo, periodTwo, TimeUnit.SECONDS);        
    }
}
