package cn.airbozh.aspect;

import cn.airbozh.utility.JwtUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

@Component
@Aspect
public class AuthorizeAspect {

    @Pointcut("execution(* cn.airbozh.controller.UserController.*(..))")
    private void logAdvicePointcut() {}

    @Before("logAdvicePointcut()")
    public void verify(){
        System.out.println("Need Auth===============================================================================================");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //token
//        String token = null;
//
//        try {
//            //token
//            token = CookieUtil.getCookieValue(request, CookieConstant.TOKEN);
//        } catch (SellException e) {
//            log.warn("【鉴权切面】{}",e.getMessage());
//
//            throw new SellerAuthorizeException();
//        }
//
//        //判断token是否存在
//        if (StringUtil.isEmpty(token)) {
//            log.warn("【鉴权切面】token不存在");
//
//            throw new SellerAuthorizeException();
//        }
//
//        //通过jwt解析token
//        Map<String, String> verifyToken = JwtUtil.verifyToken(token);
//
//        //获取openid
//        String openid = verifyToken.get(CommonConstants.OPENID_KEY);
//
//        //查询redis中的token
//        String tokenFormRedis = redisTemplate.opsForValue().get(openid, 0, -1);
//
//        //判断redis中是否查询出token
//        if (StringUtil.isEmpty(tokenFormRedis)) {
//            log.warn("【鉴权切面】redis中获取不到token");
//
//            throw new SellerAuthorizeException();
//        }
    }
}
