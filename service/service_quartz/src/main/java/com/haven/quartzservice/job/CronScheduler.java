package com.haven.quartzservice.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 向容器中添加定时任务
     * @param cron
     * @throws SchedulerException
     */
    public void scheduleJobs(String cron) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob(scheduler,cron);
    }

    /**
     * 添加一个定时任务
     * @param scheduler
     * @param cron
     * @throws SchedulerException
     */
    private void scheduleJob(Scheduler scheduler,String cron) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(CronJob.class)
                .withIdentity("cronJob", "jobGroup")
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .withSchedule(scheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

}
