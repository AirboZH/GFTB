package cn.airbozh.controller;

import cn.airbozh.pojo.User;
import cn.airbozh.service.UserService;
import cn.airbozh.utility.Auth;
import cn.airbozh.utility.ResMsg;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;
    private ResMsg<?> resMsg;
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResMsg<?> getUsers(){
        List<User> userList =  userService.getUsers();
        if (userList == null) {
            resMsg = new ResMsg<>(0, "GetUsers Fail");
        } else {
            resMsg = new ResMsg<>(1, "GetUsers Success", userList);
        }
        return resMsg;
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    @Auth
    public ResMsg<?> getUserById(@PathVariable("userId") int userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            resMsg = new ResMsg<>(0, "Find Fail");
        } else {
            resMsg = new ResMsg<>(1, "Find Success", user);
        }
        return resMsg;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResMsg<?> registerUser(@RequestBody User user){
        if(userService.userRegister(user)){
            resMsg = new ResMsg<>(1,"Register Success");
        }else {
            resMsg = new ResMsg<>(0, "Register Fail");
        }
        return resMsg;
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.PATCH)
    public ResMsg<?> updateUser(@PathVariable("userId") int userId,@RequestBody User user){
        User dbUser = userService.userUpdate(userId, user);
        if(dbUser!=null)
            resMsg = new ResMsg<>(1, "Update Success", dbUser);
        else {
            resMsg = new ResMsg<>(0, "Update Fail");
        }
        return resMsg;
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.DELETE)
    public ResMsg<?> deleteUser(@PathVariable("userId") int userId){
        if(userService.deleteUser(userId)){
            resMsg = new ResMsg<>(1,"DeleteUser Success");
        }else {
            resMsg = new ResMsg<>(0, "DeleteUser Fail");
        }
        return resMsg;
    }

}
