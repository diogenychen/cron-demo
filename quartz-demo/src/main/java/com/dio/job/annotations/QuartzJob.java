package com.dio.job.annotations;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface QuartzJob {

    String cron() default "* * * * * ?";

    String jobName() default "";

    String jobGroup() default "";

    String triggerName() default "";

    String triggerGroup() default "";

}
