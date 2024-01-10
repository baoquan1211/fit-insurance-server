package com.fit.health_insurance.model;

import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "buyer")
    private User buyer;

    // Insured person information
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private LocalDate birthdate;
    private String identityCard;
    private Integer wardId;
    private String address;

    // Insurance information
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    private Integer price;
    private Integer totalPayPerYear;
    private String benefitsId;


    // Contract information
    private LocalDate startAt;
    private LocalDate endAt;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
}