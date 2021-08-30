package com.github.vitaliimak.transporttycoon.controllers;

import com.github.vitaliimak.transporttycoon.controllers.auth.AuthUser;
import com.github.vitaliimak.transporttycoon.controllers.auth.JWTToken;
import com.github.vitaliimak.transporttycoon.models.UserDto;
import com.github.vitaliimak.transporttycoon.security.jwt.JwtProvider;
import com.github.vitaliimak.transporttycoon.services.UserAlreadyExistsException;
import com.github.vitaliimak.transporttycoon.services.UserService;
import java.net.URI;
import java.net.URISyntaxException;
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
public class UserController {

    private final JwtProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/sign-in")
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

    @PostMapping("/sign-up")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDto userDto) throws URISyntaxException {
        try {
            userService.createNewUser(userDto);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest()
                .body(e.getMessage());
        }
        return ResponseEntity.created(new URI("/api/user/sign-up")).build();
    }
}
