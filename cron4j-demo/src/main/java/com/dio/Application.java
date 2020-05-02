package com.dio;

import com.dio.job.DScheduler;
import com.dio.job.DTask;
import it.sauronsoftware.cron4j.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.sauronsoftware.cron4j.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static Map<String, String> schedulerIdMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Scheduler scheduler = DScheduler.getScheduler();

        // 1. 每小时的第5分钟执行一次： 5 * * * *
        // 2. 凌晨5点执行一次：0 5 * * *
        // 3. 每N分钟执行一次：*/N * * * *
        // 4. 每N小时执行一次：0 */N * * *
        // 5. 每N天执行一次：0 0 */N * *
        // 6. 每周一的12点到13点的每一分钟执行一次： * 12 * * Mon
        // 7. 早上九点到下午5点的时间段里，每15分钟执行一次： */15 9-17 * * *

        // 返回一个定时任务的id，通过这个id可以做一下一些事情
        // 1.移除该定时任务： scheduler.deschedule("schedulerId");
        // 2.更改定时任务执行周期：scheduler.reschedule("schedulerId", "*/2 * * * *");
        String schedulerId1 = scheduler.schedule("*/1 * * * *", () -> {
            System.out.println("每分钟一次");
        });
        String schedulerId2 = scheduler.schedule("*/2 * * * *", () -> {
            System.out.println("每两分钟一次");
        });
        //自定义task，可以定义个注解，然后通过注解扫描 添加schedule
        String schedulerId3 = scheduler.schedule("*/3 * * * *", new DTask());

        System.out.println(schedulerId1 + "----" + schedulerId2 + "----" + schedulerId3);
        schedulerIdMap.put("yourSchedulerTaskName1", schedulerId1);
        schedulerIdMap.put("yourSchedulerTaskName2", schedulerId2);
        schedulerIdMap.put("yourSchedulerTaskName3", schedulerId3);

        scheduler.start();

        Task task = scheduler.getTask("*/1 * * * *");
        System.out.println(task);


    }


}
