package com.fit.health_insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDto {
    private String name;
    private String poster;
    private String description;
    private Float interestRate;
    private Integer pricePerMonth;
    private String benefit;
    private String detail;
    private Integer totalPayPerYear;
    private Integer inpatientFeePayPerDay;
    private Integer healthCheckFeePayPerYear;
    private Integer surgicalFeePayPerYear;
    private Integer medicalVehicleFeePayPerYear;
    private Integer functionalRestorationPayPerYear;
}
