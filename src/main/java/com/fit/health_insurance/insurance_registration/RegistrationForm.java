package com.fit.health_insurance.insurance_registration;

import com.fit.health_insurance.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "registration_forms")
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrator_id")
    private User registrator;

    private String name;
    private String birthday;
    private String identityCard;
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;
    private Date createdAt;
}
