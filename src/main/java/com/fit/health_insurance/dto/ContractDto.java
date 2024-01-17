package com.fit.health_insurance.dto;

import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Integer id;
    private UserDto buyer;

    // Insured person information
    @Size(min = 10, message = "The phone must be at least 10 characters")
    private String phone;
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The gender is required")
    private String gender;
    @NotEmpty(message = "The name is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotEmpty(message = "The birthdate is required")
    private LocalDate birthdate;
    @NotEmpty(message = "The identity card is required")
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
