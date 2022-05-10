package com.haven.quartzservice.job;

import com.haven.quartzservice.mapper.TFoodMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CronJob implements Job {
    @Autowired
    TFoodMapper foodMapper;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("CronJob running");
        foodMapper.updateInventoryOntime();
    }
}
