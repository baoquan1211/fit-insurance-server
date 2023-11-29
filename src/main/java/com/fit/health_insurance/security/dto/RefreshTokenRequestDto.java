package com.fit.health_insurance.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequestDto {
    @JsonProperty("refresh")
    @NotEmpty(message = "The refresh token is required")
    private String refreshToken;
}
