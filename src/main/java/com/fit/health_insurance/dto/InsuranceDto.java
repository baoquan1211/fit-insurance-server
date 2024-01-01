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
    private Integer id;
    private String name;
    private Integer insuranceTypeId;
    private String description;
    private Integer minFeePerYear;
    private String detail;
    private String logo;
    private Integer totalPayPerYear;
    private Integer inpatientFeePayPerDay;
    private Integer healthCheckFeePayBeforeInpatientPerYear;
    private Integer healthCheckFeePayAfterInpatientPerYear;
    private Integer surgicalFeePayPerYear;
    private Integer medicalVehicleFeePayPerYear;
    private Integer functionalRestorationPayPerYear;
}
