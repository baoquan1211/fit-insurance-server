package com.fit.health_insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDto {
    private Integer id;
    private String name;
    private Integer insuranceTypeId;
    private Integer minFeePerYear;
    private String logo;
    private Integer totalPayPerYear;
    private List<InsuranceBenefitDto> benefits;
}
