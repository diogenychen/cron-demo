package com.dio.job;

import com.dio.job.annotations.QuartzJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution
@QuartzJob(cron = "*/10 * * * * ?", jobName = "helloJob")
@Component
public class HelloJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("helloJob");
    }
}
