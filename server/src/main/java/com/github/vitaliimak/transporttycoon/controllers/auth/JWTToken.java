package com.github.vitaliimak.transporttycoon.controllers.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTToken {
    private String token;
}
