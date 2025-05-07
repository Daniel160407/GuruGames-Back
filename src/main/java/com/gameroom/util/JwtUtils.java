package com.gameroom.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${JWT_SECRET}")
    private String secret;
    public String JWT_HEADER = "Authorization";
    public String JWT_PREFIX = "Bearer ";
    private final long expiration = 7 * 24 * 60 * 60 * 1000;

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    public String extractRole(String token) {
        try {
            return getClaims(token).get("role", String.class);
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
