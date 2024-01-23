package com.fit.health_insurance.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest implements Serializable {
    @NotNull(message = "Email is required")
    private String email;
    @Length(min = 12, max = 12, message = "Identify Card must be 12 numbers")
    private String identifyCard;
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    MultipartFile avatarFile;
}
