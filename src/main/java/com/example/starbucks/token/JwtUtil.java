package com.example.starbucks.token;

import com.example.starbucks.model.UserCustom;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "UmmIsStillAliveUmmIsStillAliveUmmIsStillAlive";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(UserCustom userCustom) {
        return Jwts
                .builder()
                .issuedAt(new Date(System.currentTimeMillis())) //발급시간
                .expiration(new Date(System.currentTimeMillis() + 1000 * 5)) //기한시간
                .subject(userCustom.getUserId())
                .claim("userId", userCustom.getUserId())
                .signWith(key)
                .compact();
    }

    public static boolean validToken(String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Claims extractToken(String token){
        return Jwts
                .parser() // 해석할게
                .verifyWith(key) // 키줄게
                .build() // 빌드할게
                .parseSignedClaims(token) // 토큰을 줄테니까 claim 해석할게
                .getPayload(); // payload 줄게
    }

}
