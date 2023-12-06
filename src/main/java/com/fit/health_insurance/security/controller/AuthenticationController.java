package com.fit.health_insurance.security.controller;

import com.fit.health_insurance.security.service.AuthenticationService;
import com.fit.health_insurance.security.dto.*;
import com.fit.health_insurance.exception.AuthenticationException;
import jakarta.validation.Valid;
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
            @Valid @RequestBody RegisterRequestDto request
    ) {
        authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(
            @Valid @RequestBody AuthenticationRequestDto request
    ) throws AuthenticationException {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(
            @Valid @RequestBody RefreshTokenRequestDto request
    ) throws AuthenticationException {
        authenticationService.logout(request);
    }

    @PutMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(
            @Valid @RequestBody ResetPasswordRequestDto request
    ) throws AuthenticationException {
        authenticationService.changePassword(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(
            @Valid @RequestBody RefreshTokenRequestDto request
    ) throws AuthenticationException {
        var RefreshTokenResponseDto = authenticationService.refresh(request);
        return ResponseEntity.ok(RefreshTokenResponseDto);
    }
}
