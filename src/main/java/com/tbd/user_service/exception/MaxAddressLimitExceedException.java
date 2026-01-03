package com.tbd.user_service.exception;

public class MaxAddressLimitExceedException extends RuntimeException {
    public MaxAddressLimitExceedException(String message) {
        super(message);
    }
}
