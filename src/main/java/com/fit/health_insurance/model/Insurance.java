package com.fit.health_insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer minFeePerYear;
    private String detail;
    private boolean isShow;
    private String logo;
    private Integer totalPayPerYear;
    private Integer inpatientFeePayPerDay;
    private Integer healthCheckFeePayBeforeInpatientPerYear;
    private Integer healthCheckFeePayAfterInpatientPerYear;
    private Integer surgicalFeePayPerYear;
    private Integer medicalVehicleFeePayPerYear;
    private Integer functionalRestorationPayPerYear;
}
