package com.fit.health_insurance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormRequestDto implements Serializable {
    @NotEmpty(message = "The email is required")
    @Email(message = "The email is not valid")
    private String email;
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The birthday is required")
    private String birthday;
    @NotEmpty(message = "The identityCard is required")
    @Size(min = 8, max = 12, message = "The identityCard must be 8 or 12 numbers")
    private String identityCard;
    @NotEmpty(message = "The phone number is required")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    @NotEmpty(message = "The address is required")
    private String address;
    @NotNull(message = "The file must be provided at least one")
    List<MultipartFile> file;
}
