package com.haven.quartzservice.job;

import com.haven.quartzservice.mapper.TFoodMapper;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class SchedulerListener {

    @Autowired
    public CronScheduler scheduleJobs;


    @Scheduled(cron = "0 10 22 1 5 *")
    public void schedule() throws SchedulerException {
        scheduleJobs.scheduleJobs("0 0/5 * * * ?");
//        scheduleJobs.scheduleJobs("0 0 0 1/1 * ?");
    }

//正式部署时使用
//    @Scheduled(cron = "0 51 21 1 5 *")
//    public void schedule() throws SchedulerException {
//        scheduleJobs.scheduleJobs("0/6 * * * * ?");
////        scheduleJobs.scheduleJobs("0 0 0 1/1 * ?");
//    }

}

