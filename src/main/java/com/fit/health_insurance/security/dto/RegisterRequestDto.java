package com.fit.health_insurance.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The name is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotEmpty(message = "The password is required")
    @Size(min = 6, message = "The password must be at least 6 characters")
    private String password;
}
