import cn.airbozh.controller.UserController;
import cn.airbozh.mapper.UserMapper;
import cn.airbozh.pojo.User;
import cn.airbozh.service.UserService;
import cn.airbozh.utility.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class UserTest {
//    @Test
     public void findUserByID(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController userController = (UserController) context.getBean("userController");
    }

    @Test
    public void jwtTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        User user = userService.findUserById(2);
        String token = JwtUtil.createToken(user);
        System.out.println(token);
        User tokenUser  = JwtUtil.verifyToken(token);
        System.out.println(tokenUser);
    }
    @Test
    public void test1(){
         String s = "123";
        System.out.println(false);
    }
}
