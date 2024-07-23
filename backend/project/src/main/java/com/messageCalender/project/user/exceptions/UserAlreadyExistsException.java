package com.messageCalender.project.user.exceptions;

public class UserAlreadyExistsException extends UserException {
    public UserAlreadyExistsException(String email) {
        super("User with email " + email + " already exists");
    }
}