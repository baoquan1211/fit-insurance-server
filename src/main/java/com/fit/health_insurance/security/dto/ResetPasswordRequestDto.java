package com.fit.health_insurance.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequestDto {
    @NotEmpty(message = "The email is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotEmpty(message = "The lest password is required")
    private String lastPassword;
    @NotEmpty(message = "The new password is required")
    private String newPassword;
}
