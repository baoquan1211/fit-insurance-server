package com.fit.health_insurance.security.controller;

import com.fit.health_insurance.exception.AuthenticationException;
import com.fit.health_insurance.security.dto.*;
import com.fit.health_insurance.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(
            @NotNull @Valid @RequestBody RegisterRequestDto request
    ) {
        authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody @Valid AuthenticationRequestDto request, HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.login(request, response));
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request
    ) throws AuthenticationException {
        authenticationService.logout(request);
    }

    @PutMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(
            @Valid @RequestBody ResetPasswordRequestDto request
    ) {
        authenticationService.changePassword(request);
    }

    @GetMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(HttpServletRequest request
    ) {
        var RefreshTokenResponseDto = authenticationService.refresh(request);
        return ResponseEntity.ok(RefreshTokenResponseDto);
    }
}
