/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: QuartzOne.java
 * Author: DD240
 * Date: 2015-12-8 上午11:21:56
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author> sunlj <time> <version> <desc>
 * 修改人姓名 修改日期 版本号 描述
 */
package com.javase.common.schedule;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author sunlj
 * @see
 * @since 1.0
 */
public class QuartzTestOne implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(" Genrerating report " + arg0.getJobDetail().getKey().getName() + " " +
                arg0.getJobDetail().getKey().getGroup() + " " +
                arg0.getJobDetail().getJobDataMap().getString("type"));

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

            jobDetail.getJobDataMap().put("type", "full");
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(
                            TriggerKey.triggerKey("trigger", "triggerGroup"))
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInMinutes(1).repeatForever())
                    .startAt(
                            DateBuilder.futureDate(1,
                                    DateBuilder.IntervalUnit.MINUTE)).build();

            sched.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
