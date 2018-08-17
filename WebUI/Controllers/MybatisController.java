package Controllers;

import BLL.PeopleBLL;
import BLL.UserBLL;
import btpEntity.User;
import org.junit.Test;

public class MybatisController {

    private static UserBLL objbll = UserBLL.getInstance();

    @Test
    public void add() {
        User user = new User();
        user.setName("Google");
        user.setDept("Tech");
        user.setWebsite("http://www.google.com");
        user.setPhone("120");
        objbll.InsertUser(user);

    }
}
