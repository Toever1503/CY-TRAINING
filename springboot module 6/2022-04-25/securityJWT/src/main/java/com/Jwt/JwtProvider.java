package com.Jwt;

import com.entity.UserEntity;
import com.repository.CustomUserDetailService;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Optional;

@Component
public class JwtProvider {
    private String SECRET_KEY = "secret";
    private long EXPIRATION_TIME = 864_000_000;
    private String PREFIX = "Bearer ";

    public String generateToken(CustomUserDetailService userDetailService) {
        Calendar calendar = Calendar.getInstance();
        return PREFIX + Jwts.builder().setSubject(userDetailService.getUsername())
                .setIssuedAt(calendar.getTime()).setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = this.parseToken(token);
        if(claims.getExpiration().before(new java.util.Date())) {
            return null;
        }
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        return this.parseToken(token).getExpiration().after(new java.util.Date());
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(this.PREFIX, "")).getBody();
    }


    public static void main(String[] args) {
    }
}
