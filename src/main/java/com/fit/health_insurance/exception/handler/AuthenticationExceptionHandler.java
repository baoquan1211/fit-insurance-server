package com.fit.health_insurance.exception.handler;

import com.fit.health_insurance.exception.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<?> handle(RuntimeException ex, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        result.put("error", ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

}
