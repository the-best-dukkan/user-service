package com.tbd.user_service.exception;

public class ResourceNotFoundInDbException extends RuntimeException {
    public ResourceNotFoundInDbException(String message) {
        super(message);
    }
}
