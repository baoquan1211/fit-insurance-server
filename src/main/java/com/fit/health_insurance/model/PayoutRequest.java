package com.fit.health_insurance.model;

import com.fit.health_insurance.enums.PayoutRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private PayoutRequestStatus status;
}
