package com.fit.health_insurance.auth;

import com.fit.health_insurance.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @RequestBody RegisterRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            @RequestBody RefreshTokenRequestDto request
    ) throws AuthenticationException {
        var AuthenticationResponseDto = authenticationService.refresh(request);
        return ResponseEntity.ok(AuthenticationResponseDto);
    }

}
