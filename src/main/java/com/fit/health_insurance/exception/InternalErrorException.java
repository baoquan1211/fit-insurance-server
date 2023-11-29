package com.fit.health_insurance.exception;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String message){
        super(message);
    }
}
