package IBLL;

import btpEntity.User;

import java.util.List;

public interface IUserBLL {
    //@Select("select * from user where id= #{id}")
    //public User getUserByID(int id);

    List<User> GetUserList();

    void InsertUser(User user);

    void UpdateUser(User user);

    void DeleteUser(int userId);

    User GetUser(int id);
}
