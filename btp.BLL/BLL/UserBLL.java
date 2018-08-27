package BLL;

import Common.LogHelper;
import DBUtility.MybatisDBUtilsHelper;
import IBLL.IUserBLL;
import btpEntity.User;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
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
    public List<User> GetUserList() {
        List<User> userlist = new ArrayList<User>();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userlist = userMapper.GetUserList();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return userlist;
    }

    @Override
    public void InsertUser(User user) {
        try {

            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userMapper.InsertUser(user);
            session.commit();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }

    }

    @Override
    public void UpdateUser(User user) {
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userMapper.UpdateUser(user);
            session.commit();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
    }

    @Override
    public void DeleteUser(int id) {

        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userMapper.DeleteUser(id);
            session.commit();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
    }

    @Override
    public User GetUser(int id) {
        User user = new User();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            user = userMapper.GetUser(id);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> GetAllUserList(User user) {
        List<User> userlist = new ArrayList<User>();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userlist = userMapper.GetAllUserList(user);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return userlist;
    }
}
