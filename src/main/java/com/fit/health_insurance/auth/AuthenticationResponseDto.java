package com.fit.health_insurance.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
    @JsonProperty("access")
    private String accessToken;
    @JsonProperty("refresh")
    private String refreshToken;
}
