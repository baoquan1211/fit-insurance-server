package com.fit.health_insurance.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
