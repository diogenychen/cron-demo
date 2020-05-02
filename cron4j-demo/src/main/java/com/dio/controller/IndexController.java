package com.dio.controller;

import com.dio.Application;
import com.dio.job.DScheduler;
import it.sauronsoftware.cron4j.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "index")
public class IndexController {

    @RequestMapping(value = "test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "getSchedulerMap")
    public String getSchedulerMap(){
        return Application.schedulerIdMap.toString();
    }

    @RequestMapping(value = "startSchedulerByName")
    public String startSchedulerByName(String name, String schedulePattern){
        if(Application.schedulerIdMap.containsKey(name)) {
            String schedulerId = Application.schedulerIdMap.get(name);
            DScheduler.getScheduler().reschedule(schedulerId, schedulePattern);
            return "started";
        }else{
            return "no task";
        }
    }

    @RequestMapping(value = "stopSchedulerByName")
    public String stopSchedulerByName(String name){
        if(Application.schedulerIdMap.containsKey(name)) {
            String schedulerId = Application.schedulerIdMap.get(name);
            DScheduler.getScheduler().deschedule(schedulerId);
            return "stop";
        }else{
            return "no task";
        }
    }

}
