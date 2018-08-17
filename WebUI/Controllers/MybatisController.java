package Controllers;

import BLL.UserBLL;
import btpEntity.User;
import org.apache.poi.hssf.record.UseSelFSRecord;
import org.junit.Test;

import java.util.List;

public class MybatisController {

    private static UserBLL objbll = UserBLL.getInstance();

    /**
     * 添加数据
     */
    @Test
    public void add() {
        User user = new User();
        user.setName("baidu");
        user.setDept("Tech");
        user.setWebsite("http://www.baidu.com");
        user.setPhone("13821637725");
        objbll.InsertUser(user);

    }


    /**
     * 查询数据
     */
    @Test
    public void  Search() {

      List<User>  userlist= objbll.GetUserList();

      User user=objbll.GetUser(4);

    }
}
