package com.github.vitaliimak.transporttycoon.controllers;

import com.github.vitaliimak.transporttycoon.security.jwt.JwtProvider;
import com.github.vitaliimak.transporttycoon.controllers.auth.AuthUser;
import com.github.vitaliimak.transporttycoon.controllers.auth.JWTToken;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody AuthUser authUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUser.getEmail(),
            authUser.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email or password is not valid");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new JWTToken(jwt));
    }
}
