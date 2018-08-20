package Controllers;

import Common.LogHelper;
import CommonJob.BTPPreloadJob;
import org.quartz.SchedulerException;

public class JobController {

    public static void main(String[] args)  {
        try {
            BTPPreloadJob.JobStart();
        } catch (SchedulerException ex) {
            LogHelper.Error(ex.getMessage(),ex);
        }
    }

}
