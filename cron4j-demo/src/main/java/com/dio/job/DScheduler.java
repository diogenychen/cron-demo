package com.dio.job;

import it.sauronsoftware.cron4j.Scheduler;

public class DScheduler {

    private static Scheduler scheduler = new Scheduler();

    private DScheduler(){}

    public static Scheduler getScheduler() {
        return scheduler;
    }
}
