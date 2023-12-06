package com.fit.health_insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormResponseDto {
    private String name;
    private LocalDate birthday;
    private String identityCard;
    private String phone;
    private String address;
    private List<PersonalDocumentResponseDto> files;
}
