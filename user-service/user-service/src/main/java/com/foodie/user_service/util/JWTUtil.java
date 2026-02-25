package com.foodie.user_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTUtil {
    private static final String SECRET ="hsgtekfhyskohdurjkshfyehjjsjddsssooehnchksuwufrllswijvnjskushdhekiatmckkeywnjfustskkjhbdfnnuhftsdkfhetsbfjjgbdfvdncxfn";

    private Key getSingingKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String email){
        try {
            extractEmail(token); // If parsing succeeds, token is valid
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken(Long userId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email) // Still keeping email as subject for backward compatibility
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getSingingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractRole(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

    public Long extractUserId(String token) {
        return Long.parseLong(
                Jwts.parserBuilder()
                        .setSigningKey(getSingingKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .get("userId").toString()
        );
    }

}
