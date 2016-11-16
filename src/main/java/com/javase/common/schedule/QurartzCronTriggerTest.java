/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: QurartzCronTriggerTest.java
 * Author:   DD240
 * Date:     2015-12-8 下午4:10:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.common.schedule;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
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
public class QurartzCronTriggerTest implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(" Genrerating report " + context.getJobDetail().getKey().getName() + " " +
                context.getJobDetail().getKey().getGroup() + " " +
                context.getJobDetail().getJobDataMap().getString("type"));

        System.out.println(new Date().toString());        
    }
    
    public static void main(String[] args) {
        SchedulerFactory shedFactory = new StdSchedulerFactory();
        try {
            BasicConfigurator.configure();
            Scheduler sched = shedFactory.getScheduler();
            sched.start();

            //
            JobDetail jobDetail = JobBuilder.newJob(QuartzTestOne.class)
                    .withIdentity("myJob", "group").build();

            CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("5 0/1 8-17 * * ?");
            
            jobDetail.getJobDataMap().put("type", "full");
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger", "triggerGroup")
                    .withSchedule(cronSchedule)
                    .forJob("myJob", "group")
                    .build();

            sched.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
