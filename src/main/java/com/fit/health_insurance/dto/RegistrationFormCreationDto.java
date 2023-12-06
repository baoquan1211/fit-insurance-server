package com.fit.health_insurance.dto;

import com.fit.health_insurance.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormCreationDto {
    @NotNull(message = "The registrator is required")
    private User registrator;
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The birthday is required")
    private String birthday;
    @NotEmpty(message = "The identityCard is required")
    private String identityCard;
    @NotEmpty(message = "The phone number is required")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    @NotEmpty(message = "The address is required")
    private String address;
}
