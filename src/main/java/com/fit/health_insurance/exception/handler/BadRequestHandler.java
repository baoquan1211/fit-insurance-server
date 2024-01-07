package com.fit.health_insurance.exception.handler;

import com.fit.health_insurance.exception.BadRequestException;
import com.fit.health_insurance.exception.EmailExistedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BadRequestHandler {
    @ExceptionHandler(value = {EmailExistedException.class, BadRequestException.class})
    public ResponseEntity<?> handle(RuntimeException ex, HttpServletRequest request) {
        Map result = new HashMap<>();
        result.put("error", ex.getMessage());
        result.put("status", 400);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
