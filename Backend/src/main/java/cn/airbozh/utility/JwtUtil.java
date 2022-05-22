package cn.airbozh.utility;

import cn.airbozh.pojo.User;
import cn.airbozh.utility.Auth.AuthException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;

import java.security.Key;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String createToken(User user) {
        String subject;
        try {
            subject = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Jwts.builder().setSubject(subject).signWith(key).compact();
    }

    public static User verifyToken(String token) {
        try {
            String userInfo = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
            return objectMapper.readValue(userInfo, User.class);
        } catch (Exception e) {
            throw new AuthException(HttpStatus.UNAUTHORIZED, "认证不通过");
        }
    }
}

