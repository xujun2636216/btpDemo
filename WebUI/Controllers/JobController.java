package Controllers;

import Common.LogHelper;
import Job.JobTrigger;
import org.quartz.SchedulerException;

public class JobController {

    public static void main(String[] args)  {
        try {
            JobTrigger.JobStart();
        } catch (SchedulerException ex) {
            LogHelper.Error(ex.getMessage(),ex);
        }
    }

}
