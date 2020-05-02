package com.dio.job;

import com.dio.job.annotations.QuartzJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class JobListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        Map<String, Object> quartzCronMap = applicationContext.getBeansWithAnnotation(QuartzJob.class);
        Set<Map.Entry<String, Object>> entries = quartzCronMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            QuartzJob annotation = entry.getValue().getClass().getAnnotation(QuartzJob.class);
            //Get information from Annotation
            String packageName = entry.getValue().getClass().getPackage().getName();
            String jobName = annotation.jobName().isEmpty() ? entry.getKey() : annotation.jobName();
            String groupName = annotation.jobGroup().isEmpty() ? packageName : annotation.jobGroup();
            //JobDetail defined
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity(jobName, groupName).build();

            //Trigger defined
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(annotation.cron())
                    ).build();
            //Add Job
            JobUtils.addJob(jobDetail, trigger);
        }
    }
}
