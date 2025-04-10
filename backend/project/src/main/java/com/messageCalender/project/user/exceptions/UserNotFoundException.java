package com.messageCalender.project.user.exceptions;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String identifier) {
        super("User not found with identifier: " + identifier);
    }
}