package com.github.vitaliimak.transporttycoon.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ignored) {
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .build()
            .parseClaimsJws(token)
            .getBody();

        User user = new User(claims.getSubject(), "", emptyList());

        return new UsernamePasswordAuthenticationToken(user, token, emptyList());
    }

    public String createToken(Authentication authentication) {
        return null;
    }
}
