package BLL;

import Common.LogHelper;
import DBUtility.MybatisDBUtilsHelper;
import IBLL.IUserBLL;
import btpEntity.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserBLL implements IUserBLL {
    // 获取Session连接
    private static SqlSession session = MybatisDBUtilsHelper.getSession().openSession();

    //创建 SingleObject 的一个对象
    private static UserBLL instance = null;

    //获取唯一可用的对象
    public static UserBLL getInstance() {
        if (instance == null) {
            instance = new UserBLL();
        }
        return instance;
    }


    @Override
    public  List<User> GetUserList() {
        return null;
    }

    @Override
    public void InsertUser(User user) {
        try {

            // 获取Mapper
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            // 执行插入
            userMapper.InsertUser(user);
            // 提交事务
            session.commit();
        } catch (Exception ex) {
                LogHelper.Error(ex.getMessage(), ex);
        }

    }

    @Override
    public void UpdateUser(User user) {

    }

    @Override
    public void DeleteUser(int userId) {

    }

    @Override
    public User GetUser(int id) {
        return null;
    }
}
