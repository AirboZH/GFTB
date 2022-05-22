package cn.airbozh.service;

import cn.airbozh.pojo.User;

import java.util.List;

public interface UserService {
    User findUserById(int userId);
    User findUserByName(String userName);
    List<User> getUsers();
    boolean userRegister(User user);
    User userUpdate(int userId,User user);
    boolean deleteUser(int userId);
    boolean userLogin(User user);
    String createToken(User user);
    User verifyToken(String token);

    float getMoney(int userId);
}
