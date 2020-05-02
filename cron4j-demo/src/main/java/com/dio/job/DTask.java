package com.dio.job;

import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

public class DTask extends Task {

    @Override
    public void execute(TaskExecutionContext context) throws RuntimeException {
        System.out.println("i am a task");
    }

    @Override
    public boolean canBePaused() {
        return true;
    }

    @Override
    public boolean canBeStopped() {
        return true;
    }


}
