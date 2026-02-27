package com.foodie.order_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {
    private final String SECRET = "hsgtekfhyskohdurjkshfyehjjsjddsssooehnchksuwufrllswijvnjskushdhekiatmckkeywnjfustskkjhbdfnnuhftsdkfhetsbfjjgbdfvdncxfn";

    private Key getSingingKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    public Long extractUserId(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();

        return Long.valueOf(claims.get("userId").toString());
    }
}
