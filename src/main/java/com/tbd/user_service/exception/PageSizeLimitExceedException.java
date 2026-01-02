package com.tbd.user_service.exception;

public class PageSizeLimitExceedException extends RuntimeException {
    public PageSizeLimitExceedException(String message) {
        super(message);
    }
}
