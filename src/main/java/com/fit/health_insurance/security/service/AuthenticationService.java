package com.fit.health_insurance.security.service;

import com.fit.health_insurance.security.model.Token;
import com.fit.health_insurance.security.repository.TokenRepository;
import com.fit.health_insurance.security.dto.*;
import com.fit.health_insurance.exception.AuthenticationException;
import com.fit.health_insurance.exception.EmailExistedException;
import com.fit.health_insurance.enums.Role;
import com.fit.health_insurance.model.User;
import com.fit.health_insurance.repository.UserRepository;
import com.fit.health_insurance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDto request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .role(Role.USER)
                .createdAt(new Date())
                .build();
        try {
            userRepository.save(user);
        } catch (RuntimeException ex) {
            throw new EmailExistedException("The email is existed");
        }
    }
    private void usernamePasswordAuthentication(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
        } catch (RuntimeException ex) {
            throw new AuthenticationException("Email and password do not match");
        }
    }
    public AuthenticationResponseDto login(AuthenticationRequestDto request) throws AuthenticationException {
        usernamePasswordAuthentication(request.getEmail(), request.getPassword());
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, refreshToken);
        return new AuthenticationResponseDto(accessToken, refreshToken);
    }

    public void changePassword(ResetPasswordRequestDto request) throws AuthenticationException {
        usernamePasswordAuthentication(request.getEmail(), request.getLastPassword());
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void logout(RefreshTokenRequestDto request) throws AuthenticationException {
        jwtService.revokeToken(request.getRefreshToken());
        SecurityContextHolder.clearContext();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    public RefreshTokenResponseDto refresh(RefreshTokenRequestDto refreshTokenRequest) throws AuthenticationException {
        final String refreshToken = refreshTokenRequest.getRefreshToken();
        if (refreshToken != null) {
            try {
                var userEmail = jwtService.extractEmail(refreshToken);
                var user = userService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(refreshToken, user) && jwtService.isTokenRevoked(refreshToken, (User) user)) {
                    var accessToken = jwtService.generateAccessToken(user);
                    saveUserToken((User) user, accessToken);
                    return new RefreshTokenResponseDto(accessToken);
                }
            } catch (RuntimeException ex) {
                throw new AuthenticationException("Refresh token not valid.");
            }
        }
        throw new AuthenticationException("Refresh token is not valid or expired.");
    }
}
