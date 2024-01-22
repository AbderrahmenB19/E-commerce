package com.jwtyt.ecommerce.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
@Component

public class JwtGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String Generate(Authentication authentication){
        String username= authentication.getName();
        Date currenteDate= new Date();

        Date expireDate=new Date(currenteDate.getTime()+SecurityConstant.JWT_EXPIRATION);
        String Token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
        return Token;


    }

    public String getUsernameFormToken(String Token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(Token)
                .getBody()
                .getSubject();
    }
    public Boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
                    return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT was Expired or in correct ",e.fillInStackTrace());

        }
    }
}
