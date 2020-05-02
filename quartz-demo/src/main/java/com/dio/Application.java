package com.dio;

import com.dio.job.HelloJob;
import com.dio.job.JobUtils;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        JobUtils.jobStart();

        //手动添加定时任务
        /*JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "group").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(30)
                        .repeatForever()
                ).build();

        Date date = JobUtils.addJob(jobDetail, trigger);
        System.out.println(date);

        System.out.println("myJob jobKey: " + JobKey.jobKey("myJob"));*/

    }

}
