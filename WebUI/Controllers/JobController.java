package Controllers;

import Common.LogHelper;
import Job.JobHelper;
import org.quartz.SchedulerException;

public class JobController {

    public static void main(String[] args)  {
        try {
            JobHelper.JobStart();
        } catch (SchedulerException ex) {
            LogHelper.Error(ex.getMessage(),ex);
        }
    }

}
