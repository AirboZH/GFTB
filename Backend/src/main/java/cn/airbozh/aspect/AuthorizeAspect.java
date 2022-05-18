package cn.airbozh.aspect;

import cn.airbozh.utility.Auth.AuthException;
import cn.airbozh.utility.JwtUtil;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class AuthorizeAspect {
    @Pointcut("@annotation(cn.airbozh.utility.Auth.Auth)")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token;
        String info;
        token = request.getHeader("Authorization");

        if (token == null)
            throw new AuthException(HttpStatus.BAD_REQUEST, "无Token");
        token = token.split("Bearer ")[1];
        info = JwtUtil.verifyToken(token).getBody().getSubject();
        System.out.println("userId:"+info);
    }
//    @After("pointCut()")
//    public void after(){
//        System.out.println("-----------------------");
//    }
//    @AfterReturning("pointCut()")
//    public void afterReturning(){
//        System.out.println("+++++++++++++++");
//    }
}
