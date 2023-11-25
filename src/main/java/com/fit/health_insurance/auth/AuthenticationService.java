package com.fit.health_insurance.auth;

import com.fit.health_insurance.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final UserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ITokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto register(RegisterRequestDto request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .role(Role.USER)
                .createdAt(new Date())
                .build();
        try {
            var savedUser = userRepository.save(user);
            return UserDto.builder()
                    .id(savedUser.getId())
                    .email(savedUser.getEmail())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public AuthenticationResponseDto login(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, accessToken);
        return new AuthenticationResponseDto(accessToken, refreshToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponseDto refresh(RefreshTokenRequestDto refreshTokenRequest) throws AuthenticationException {
        final String refreshToken = refreshTokenRequest.getRefreshToken();
        if (refreshToken != null) {
            var userEmail = jwtService.extractEmail(refreshToken);
            var user = userService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateAccessToken(user);
                saveUserToken((User) user, accessToken);
                return new AuthenticationResponseDto(accessToken, refreshToken);
            }
        }
        throw new AuthenticationException("Refresh token not found");
    }
}
