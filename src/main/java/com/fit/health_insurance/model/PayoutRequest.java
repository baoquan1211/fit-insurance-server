package com.fit.health_insurance.model;

import com.fit.health_insurance.enums.PayoutRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payout_request")
public class PayoutRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private PayoutRequestStatus status;
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "payout_request_benefit",
            joinColumns = { @JoinColumn(name = "payout_request_id") },
            inverseJoinColumns = { @JoinColumn(name = "benefit_id") }
    )
    private Set<InsuranceBenefit> benefits;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    private Integer totalPay;
}
