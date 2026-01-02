package com.tbd.user_service.exception;

public class UserSubNotFoundInHeaderException extends RuntimeException {
    public UserSubNotFoundInHeaderException(String message) {
        super(message);
    }
}
