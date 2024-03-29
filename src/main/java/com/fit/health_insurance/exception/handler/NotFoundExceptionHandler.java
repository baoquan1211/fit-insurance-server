package com.fit.health_insurance.exception.handler;

import com.fit.health_insurance.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handle(RuntimeException ex, HttpServletRequest request) {
        Map result = new HashMap<>();
        result.put("error", ex.getMessage());
        result.put("status", 404);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
