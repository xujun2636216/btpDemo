package BLL;

import Common.LogHelper;
import DBUtility.MybatisDBUtilsHelper;
import IBLL.IUserBLL;
import btpEntity.User;
import org.apache.ibatis.session.SqlSession;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class UserBLL implements IUserBLL {

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
        final SqlSession session = MybatisDBUtilsHelper.getSession().openSession();
        List<User> userlist = new ArrayList<User>();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userlist = userMapper.GetUserList();
        } catch (Exception ex) {
            session.rollback();
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return userlist;
    }

    @Override
    public void InsertUser(User user) {
        final SqlSession session = MybatisDBUtilsHelper.getSession().openSession();
        try {

            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userMapper.InsertUser(user);
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }

    }

    @Override
    public void UpdateUser(User user) {
        final SqlSession session = MybatisDBUtilsHelper.getSession().openSession();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userMapper.UpdateUser(user);
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
    }

    @Override
    public void DeleteUser(int id) {
        final SqlSession session = MybatisDBUtilsHelper.getSession().openSession();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userMapper.DeleteUser(id);
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
    }

    @Override
    public User GetUser(int id) {
        final SqlSession session = MybatisDBUtilsHelper.getSession().openSession();
        User user = new User();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            user = userMapper.GetUser(id);
        } catch (Exception ex) {
            session.rollback();
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> GetAllUserList(User user) {
        final SqlSession session = MybatisDBUtilsHelper.getSession().openSession();
        List<User> userlist = new ArrayList<User>();
        try {
            IUserBLL userMapper = session.getMapper(IUserBLL.class);
            userlist = userMapper.GetAllUserList(user);
        } catch (Exception ex) {
            session.rollback();
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return userlist;
    }
}
