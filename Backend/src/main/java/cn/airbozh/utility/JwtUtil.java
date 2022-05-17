package cn.airbozh.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    public String createToken() {

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder().setSubject("NB").signWith(key).compact();
    }

    public boolean verifyToken() {
//        return Jwts.parser().isSigned(key).build().parseClaimsJws(jws).getBody().getSubject().equals("NB");
        return false;
    }
}
