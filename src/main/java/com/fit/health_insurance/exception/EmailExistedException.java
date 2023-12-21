package com.fit.health_insurance.exception;


public class EmailExistedException extends RuntimeException {
    public EmailExistedException(String message) {
        super(message);
    }
}
