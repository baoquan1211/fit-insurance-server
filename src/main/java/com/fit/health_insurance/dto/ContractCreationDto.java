package com.fit.health_insurance.dto;

import com.fit.health_insurance.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreationDto implements Serializable {
    @Email(message = "The buyer email is not valid")
    private String buyer;
    // Insured person information
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    @NotEmpty(message = "The phone number is required")
    private String phone;
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The gender is required")
    private String gender;
    @Email(message = "The email is not valid")
    private String email;
    @NotEmpty(message = "The birthdate is required")
    private String birthdate;
    @NotEmpty(message = "The identityCard is required")
    private String identityCard;
    @NotNull(message = "The province is required")
    private Integer province;
    @NotNull(message = "The district is required")
    private Integer district;
    @NotNull(message = "The ward is required")
    private Integer ward;
    @NotEmpty(message = "The street is required")
    private String street;

    // Insurance information
    private Integer insurance;

    // Contract information
    private String startAt;

    public void validate() {
        if (!this.name.matches("^[A-Z\s]+$"))
            throw new BadRequestException("Tên in hoa không chứa dấu hay ký tự đặc biệt");
    }
}
