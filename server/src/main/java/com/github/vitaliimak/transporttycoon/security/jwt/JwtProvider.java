package com.github.vitaliimak.transporttycoon.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class JwtProvider {
    private final SecretKey key;

    @Value("${jwt.expirationTime}")
    private Long tokenExpirationTime;

    public JwtProvider() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ignored) {
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

        User user = new User(claims.getSubject(), "", emptyList());
        return new UsernamePasswordAuthenticationToken(user, token, emptyList());
    }

    public String createToken(Authentication authentication) {
        return Jwts.builder()
            .setSubject(authentication.getName())
            .signWith(key)
            .setExpiration(new Date((new Date()).getTime() + tokenExpirationTime))
            .compact();
    }
}
