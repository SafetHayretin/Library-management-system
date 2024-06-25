package com.safetKyuchyukhalil.libraryManagementSystem.exception.users;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
