package com.fit.health_insurance.exception;

import lombok.AllArgsConstructor;

public class EmailExistedException extends RuntimeException {
    public EmailExistedException(String message) {
        super(message);
    }
}
