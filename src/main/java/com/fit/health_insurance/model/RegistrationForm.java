package com.fit.health_insurance.model;

import com.fit.health_insurance.enums.Period;
import com.fit.health_insurance.enums.RegistrationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "registration_form")
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrator_id")
    private User registrator;
    private String name;
    private LocalDate birthday;
    private String identityCard;
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @Enumerated(EnumType.STRING)
    private Period periodPay;
    private Date createdAt;
    public RegistrationForm() {
        this.createdAt = new Date();
    }
}
