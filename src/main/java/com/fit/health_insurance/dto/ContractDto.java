package com.fit.health_insurance.dto;

import com.fit.health_insurance.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Integer id;
    private UserDto buyer;

    // Insured person information
    private String phone;
    private String name;
    private String gender;
    private String email;
    private LocalDate birthdate;
    private String identityCard;
    private Integer wardId;
    private String address;

    // Insurance information
    private InsuranceDto insurance;
    private Integer price;
    private Integer totalPayPerYear;
    private String benefitsId;
    // Contract information
    private LocalDate startAt;
    private LocalDate endAt;
    private String status;
}
