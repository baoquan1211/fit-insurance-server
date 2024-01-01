package com.fit.health_insurance.dto;

import com.fit.health_insurance.enums.PaymentStatus;
import com.fit.health_insurance.enums.PaymentType;
import com.fit.health_insurance.model.Contract;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer id;
    private String status;
    private Integer transactionId;
    private String type;
    private Integer amount;
    private String payDate;
    private Integer contractId;
}
