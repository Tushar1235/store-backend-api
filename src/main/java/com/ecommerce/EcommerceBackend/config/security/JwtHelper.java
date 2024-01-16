package com.ecommerce.EcommerceBackend.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;
import java.time.Instant;

@Component
public class JwtHelper {
    private static final long JWT_TOKEN_VALID = 2 * 60 * 60 ;
    private   final String secret = "cdoijgt675r4N1km5xv1yV0t4OUZfde553ghzvmhgjXSDp89wdlHyrLaXQtHpWvoy";

    public String createSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[36]; //36*8=288 (>256 bits required for HS256)
        secureRandom.nextBytes(secretBytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(secretBytes);
    }

    //retrieve username from token
    public String getUserName(String token){
        return getClaimsFromToken(token,Claims::getSubject);
    }
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims allClaims = getAllClaimsFromToken(token);
        return claimsResolver.apply(allClaims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token){
        Date date = getExpirationDate(token);
        return  date.before(new Date());
    }
    public Date getExpirationDate(String token){
        return getClaimsFromToken(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return  doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return  Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(Instant.now().toEpochMilli()))
                .setExpiration(new Date(Instant.now().plusSeconds(JWT_TOKEN_VALID).toEpochMilli()))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
    //validate token
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUserName(token);
        return (userDetails.getUsername().equals(username) && isTokenExpired(token));
    }
}
