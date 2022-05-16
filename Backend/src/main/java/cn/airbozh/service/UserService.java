package cn.airbozh.service;

import cn.airbozh.pojo.User;

import java.util.List;

public interface UserService {
    User findUserById(int userId);
    List<User> getUsers();
    boolean userRegister(User user);
    User userUpdate(int userId,User user);
    boolean deleteUser(int userId);
}
