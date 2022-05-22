package cn.airbozh.aspect;

import cn.airbozh.pojo.User;
import cn.airbozh.utility.Auth.AuthException;
import cn.airbozh.utility.JwtUtil;
import org.aspectj.lang.JoinPoint;
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
public class AuthAspect {
    @Pointcut("@annotation(cn.airbozh.utility.Auth.Auth)")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token;
        token = request.getHeader("Authorization");

        if (token == null)
            throw new AuthException(HttpStatus.BAD_REQUEST, "无Token");
        try {
            token = token.split("Bearer ")[1];
        } catch (Exception e) {
            throw new AuthException(HttpStatus.BAD_REQUEST, "无效Token");
        }
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof User) {
                ((User) arg).setUserId(JwtUtil.verifyToken(token).getUserId());
            }
        }

    }
}
