package com.example.hucodeuz.security;

import com.example.hucodeuz.exception.ResourceNotFoundException;
import io.jsonwebtoken.*;
import org.aspectj.weaver.SignatureUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author "Husniddin Ulachov"
 * @created 3:52 AM on 7/17/2022
 * @project Edu-Center
 */
@Component
public class JwtProvider {
    @Value("${jwt.secretkey}")
    private String key;
    @Value("${jwt.token.ttl}")
    private Long ttl;

    public String generateToken(String  username){
       return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ttl))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    public String getUsernameFromToken(String token){
       return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isExpiredToken(String token) {
        try {
            Date expiration = Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.after(new Date());
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        }
        catch(MalformedJwtException e){
            System.err.println("Invalid JWT token");
        }
        catch(ExpiredJwtException e){
            System.err.println("Expired JWT token");
        }
        catch(UnsupportedJwtException e){
            System.err.println("Unsupported JWT token");
        }
        catch(IllegalArgumentException e){
            System.err.println("JWT claims string is empty");
        }
    return false;
    }
}
