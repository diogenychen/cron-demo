package com.dio.job.annotations;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface QuartzJob {

    //cron表达式，从左到右分别是秒、分、时、天、月、星期/年
    String cron() default "* * * * * ?";

    //可以不定义，为空时会默认获取类名，可以根据该名字暂停或开启一个定时任务
    String jobName() default "";

    //可以不定义，为空时会默认获取包名
    String jobGroup() default "";

    String triggerName() default "";

    String triggerGroup() default "";

}
