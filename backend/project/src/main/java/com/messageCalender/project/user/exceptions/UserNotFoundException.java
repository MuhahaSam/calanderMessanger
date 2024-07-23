package com.messageCalender.project.user.exceptions;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}