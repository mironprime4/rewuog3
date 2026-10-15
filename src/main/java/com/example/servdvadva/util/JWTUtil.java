package com.example.servdvadva.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;


@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private final String secret = "GpYvQNcDqdNetz9WvhUDbgSnNMQuJdqsEC0E7S-bhLLLBgR3u25IwCcTcsMj9wnn";

    public String generateToken(String username){
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Date expirationDate = (Date) Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        return Jwts.builder()
                .subject(username)
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .issuer("mironprime")
                .signWith(secretKey)
                .compact();
    }

    public String validateTokenAndRetrieveSubject(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .requireIssuer("mironprime")
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
