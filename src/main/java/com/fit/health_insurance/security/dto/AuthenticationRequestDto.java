package com.fit.health_insurance.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDto {
    @NotEmpty(message = "The email is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotEmpty(message = "The password is required")
    private String password;
}
