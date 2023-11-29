package com.fit.health_insurance.insurance_registration.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
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
