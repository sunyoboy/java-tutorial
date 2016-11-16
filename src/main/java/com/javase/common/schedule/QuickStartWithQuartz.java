/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: QuickStartWithQuartz.java
 * Author:   DD240
 * Date:     2015-12-8 下午2:49:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.common.schedule;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class QuickStartWithQuartz implements Job {
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        System.out.println(arg0.getJobDetail().getKey().getName() + " " + 
                arg0.getJobDetail().getKey().getGroup());
        System.out.println(arg0.getTrigger().getKey().getName() + " " + 
                arg0.getTrigger().getKey().getGroup());
        System.out.println(new Date().toString());
        System.out.println(Thread.currentThread().getId() + " \t" + 
                Thread.currentThread().getName() + " \t" +
                Thread.currentThread().getContextClassLoader() + " \t" + 
                Thread.currentThread().getThreadGroup() + " \t" +
                Thread.currentThread().getState());
    }
    
    static Logger logger = Logger.getLogger(QuickStartWithQuartz.class);
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see 
     * @since 
     */
    public static void main(String[] args) {
        try {
            // get the default scheduler from factory
            BasicConfigurator.configure();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            // define the job and tie to the QuickStartWithQuartz class
            JobDetail jobDetail = JobBuilder.newJob(QuickStartWithQuartz.class).withIdentity("job1", "goup1").build();
            
            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger  = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(4000)
                            .repeatForever())
                    .build();
            
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            Thread.sleep(60000);
            scheduler.shutdown();
            logger.error("This is error message.");  
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
