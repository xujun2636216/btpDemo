package IBLL;

import btpEntity.User;

import java.util.List;

public interface IUserBLL {
    //@Select("select * from user where id= #{id}")
    // User getUserByID(int id);

    List<User> GetUserList();

    void InsertUser(User user);

    void UpdateUser(User user);

    void DeleteUser(int id);

    User GetUser(int id);

    List<User> GetAllUserList(User user);
}
