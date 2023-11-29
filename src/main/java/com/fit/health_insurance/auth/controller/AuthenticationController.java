package com.fit.health_insurance.auth.controller;

import com.fit.health_insurance.auth.service.AuthenticationService;
import com.fit.health_insurance.auth.dto.*;
import com.fit.health_insurance.exception.AuthenticationException;
import com.fit.health_insurance.user.dto.UserDto;
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

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody RegisterRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
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

    @PostMapping("/test")
    public ResponseEntity<String> test(
    ) throws AuthenticationException {
        return ResponseEntity.ok("Test");
    }


}
