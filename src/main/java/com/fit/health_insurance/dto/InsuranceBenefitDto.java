package com.fit.health_insurance.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceBenefitDto {
    private Integer id;
    private String name;
    private String unit;
    private Integer amount;
    private Integer insuranceId;
    private boolean isActive;
}
