package Controllers;

import BLL.UserBLL;
import Common.ExecHelper;
import btpEntity.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        int page = 2;
        int pageSize = 5;
        List<User> userlist = objbll.GetUserList();
        //分页技术
        userlist = userlist.parallelStream().skip((page - 1) * pageSize).collect(Collectors.toList());
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


    /**
     * 查询数据(记住一对多查询，多对对，一对一主表和字表的主键id不能相同)
     */
    @Test
    public void Searchlist() {
        User user = new User();
        user.setPhone("1223443545");
        List<User> userlist = objbll.GetAllUserList(user);
    }


    /**
     * 导出excel
     */
    @Test
    public void DownExcel() {

        List<User> userlist = objbll.GetUserList();
        String[] arr = userlist.toArray(new String[userlist.size()]);
        try {
            ExecHelper.exec(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
