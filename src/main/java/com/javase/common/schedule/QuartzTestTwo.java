/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: QuartzTestTwo.java
 * Author: DD240
 * Date: 2015-12-8 下午2:09:18
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author> sunlj <time> <version> <desc>
 * 修改人姓名 修改日期 版本号 描述
 */
package com.common.schedule;

import java.util.ArrayList;

import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import ch.qos.logback.classic.BasicConfigurator;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author sunlj
 * @see
 * @since 1.0
 */
public class QuartzTestTwo implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // String


        String instName = arg0.getJobDetail().getKey().getName();
        String groupName = arg0.getJobDetail().getKey().getGroup();
        JobDataMap jobDataMap = arg0.getJobDetail().getJobDataMap();

        //
        String description = jobDataMap.getString("description");
        int value = jobDataMap.getInt("value");
        ArrayList<String> array = (ArrayList<String>) jobDataMap.get("list");

        System.out.println("QuartzTestTwo [instName=" + instName
                + ", groupName=" + groupName + ", jobDataMap=" + jobDataMap
                + ", description=" + description + ", value=" + value
                + ", array=" + array + "]");

    }

    public static void main(String[] args) {
        SchedulerFactory factory = new StdSchedulerFactory();
        try {
            Scheduler sched = factory.getScheduler();
            sched.start();

            JobDetail jobDetail = JobBuilder.newJob(QuartzTestTwo.class)
                    .withIdentity("myJob", "myGroup").build();
            jobDetail.getJobDataMap().put("description", "my job description");
            jobDetail.getJobDataMap().put("value", 1998);
            ArrayList<String> list = new ArrayList<String>();
            list.add("item");
            jobDetail.getJobDataMap().put("list", list);

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger", "griggerGroup")
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMinutes(1).repeatForever())
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.MINUTE))
                    .build();
            sched.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
