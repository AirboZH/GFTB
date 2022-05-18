package cn.airbozh.utility;

import cn.airbozh.utility.Auth.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;

import java.security.Key;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(String info) {
        return Jwts.builder().setSubject(info).signWith(key).compact();
    }

    public static Jws<Claims> verifyToken(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (Exception e) {
            throw new AuthException(HttpStatus.UNAUTHORIZED, "认证不通过");
        }
    }
}

