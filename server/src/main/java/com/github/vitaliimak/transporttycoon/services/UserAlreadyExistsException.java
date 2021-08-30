package com.github.vitaliimak.transporttycoon.services;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("Email already exists");
    }
}
