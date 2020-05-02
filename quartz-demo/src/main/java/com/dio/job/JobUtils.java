package com.dio.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JobUtils {

    private static Logger LOG = LoggerFactory.getLogger(JobUtils.class);

    //Scheduler静态化，单例
    private static Scheduler scheduler;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加定时任务
     */
    public static Date addJob(JobDetail jobDetail, Trigger trigger){
        try {
            Date date = scheduler.scheduleJob(jobDetail, trigger);
            LOG.info("JobUtils addJob, jobName={}, date={}", jobDetail.getKey().getName(), date);
            return date;
        } catch (SchedulerException e) {
            LOG.error("JobUtils addJob failed, jobName={}, e={}", jobDetail.getKey().getName(), e);
        }
        return null;
    }

    /**
     * 开始任务
     */
    public static void jobStart(){
        try {
            scheduler.start();
            LOG.info("JobUtils jobStart");
        } catch (SchedulerException e) {
            LOG.error("JobUtils jobStart failed", e);
        }
    }

    /**
     * 停止所有任务
     */
    public static void jobPauseAll() {
        try {
            scheduler.pauseAll();
            LOG.info("JobUtils jobPauseAll");
        } catch (SchedulerException e) {
            LOG.error("JobUtils jobPauseAll failed", e);
        }
    }

    /**
     * 停止指定任务
     */
    public static void jobPause(String jobName) {
        try {
            scheduler.pauseJob(new JobKey(jobName));
            LOG.info("JobUtils jobPause, jobName={}", jobName);
        } catch (SchedulerException e) {
            LOG.error("JobUtils jobPause failed, jobName={}, e={}", jobName, e);
        }
    }




}

