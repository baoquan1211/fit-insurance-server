package com.fit.health_insurance.model;

import com.fit.health_insurance.enums.ContractStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    // Insured person information
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    private String name;
    private LocalDate birthdate;
    private String identityCard;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_id")
    private Ward ward;
    private String address;

    // Insurance information
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    private Integer price;
    private Integer totalPayPerYear;
    private Integer inpatientFeePayPerDay;
    private Integer healthCheckFeePayBeforeInpatientPerYear;
    private Integer healthCheckFeePayAfterInpatientPerYear;
    private Integer surgicalFeePayPerYear;
    private Integer medicalVehicleFeePayPerYear;
    private Integer functionalRestorationPayPerYear;

    // Contract information
    private LocalDate startAt;
    private LocalDate endAt;
    private LocalDate paymentExpiredAt;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
}