package com.fit.health_insurance.model;

import com.fit.health_insurance.enums.PaymentStatus;
import com.fit.health_insurance.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "payment")
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Integer transactionId;
    private PaymentType type;
    private Integer amount;
    private Date payDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Contract contract;
}
