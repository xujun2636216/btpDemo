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
    public void Search() {

        List<User> userlist = objbll.GetUserList();

        User user = objbll.GetUser(4);

    }

    /**
     * 修改和更新
     */
    @Test
    public void ModifyandDel() {

        User user = objbll.GetUser(4);
        user.setName("www");
        user.setPhone("1223443545");
        user.setWebsite("sdfsdf");

        objbll.UpdateUser(user);

        objbll.DeleteUser(6);
    }


}
