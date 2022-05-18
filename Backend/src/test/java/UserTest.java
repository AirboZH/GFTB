import cn.airbozh.controller.UserController;
import cn.airbozh.mapper.UserMapper;
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
        System.out.println(userController.getUsers());
    }

    @Test
    public void jwtTest(){
        String token = JwtUtil.createToken("111");
        System.out.println(token);
        Jws<Claims> claims = JwtUtil.verifyToken(token);
        System.out.println(claims);
        Claims s = claims.getBody();
        System.out.println(s.getSubject());

    }
}
