package CommonJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class BTPPreloadJob {

    public  static  void  JobStart() throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        // 从工厂里面拿到一个scheduler实例
        Scheduler scheduler = factory.getScheduler();

        JobKey jobKeyA = new JobKey("jobA", "group1");
        JobDetail jobA = JobBuilder.newJob(JobA.class)
                .withIdentity(jobKeyA).build();

        JobKey jobKeyB = new JobKey("jobB", "group1");
        JobDetail jobB = JobBuilder.newJob(JobB.class)
                .withIdentity(jobKeyB).build();


        Trigger trigger1 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName1", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();


        Trigger trigger2 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName2", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobA, trigger1);
        scheduler.scheduleJob(jobB, trigger2);
        scheduler.start();
    }
}
