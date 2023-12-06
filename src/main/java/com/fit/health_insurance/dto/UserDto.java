package com.fit.health_insurance.dto;

import com.fit.health_insurance.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String address;
    private String phone;
    private String identityCard;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
