package com.fit.health_insurance.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class AccessDeniedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity handleAccessDeniedException() {
        Map result = new HashMap<>();
        result.put("error", "Do not have permission");
        result.put("status", 403);
        return new ResponseEntity(result, HttpStatus.FORBIDDEN);
    }
}
