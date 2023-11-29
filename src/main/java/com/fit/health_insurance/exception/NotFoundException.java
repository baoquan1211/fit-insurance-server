package com.fit.health_insurance.exception;

import lombok.RequiredArgsConstructor;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
