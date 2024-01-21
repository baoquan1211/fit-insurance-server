package com.fit.health_insurance.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest implements Serializable {
    @NotNull(message = "Email is required")
    private String email;
    private String phone;
    private String identifyCard;
    MultipartFile avatarFile;
}
