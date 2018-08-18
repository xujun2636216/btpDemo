package Job;

import BLL.UserBLL;
import Common.LogHelper;
import btpEntity.User;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobB implements Job {

    private static UserBLL objbll = UserBLL.getInstance();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            User user = new User();
            user.setName("baidu");
            user.setDept("Tech");
            user.setWebsite("http://www.baidu.com");
            user.setPhone("13821637725");
            objbll.InsertUser(user);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        }
    }
}
