package cn.airbozh.service.impl;

import cn.airbozh.mapper.UserMapper;
import cn.airbozh.pojo.User;
import cn.airbozh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserById(int userId) {
        try {
            return userMapper.findUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public boolean userRegister(User user) {
        if (userMapper.findUserByName(user.getUserName()) == null) {
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            try {
                return userMapper.createUser(user) != 0;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            return false;
    }

    @Override
    public User userUpdate(int userId, User user) {
        User dbUser = userMapper.findUserById(userId);
        boolean flag = false;
        if (user.getUserName() != null) {
            dbUser.setUserName(user.getUserName());
            flag = true;
        }
        if (user.getAddress() != null) {
            dbUser.setAddress(user.getAddress());
            flag = true;
        }
        if (flag) {
            dbUser.setUpdateDate(new Date());
            if (userMapper.updateUser(dbUser) != 0)
                return userMapper.findUserById(userId);
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return userMapper.deleteUser(userId) != 0;
    }


}
