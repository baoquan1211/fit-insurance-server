package com.fit.health_insurance.security.dto;

import com.fit.health_insurance.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The email is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotEmpty(message = "The password is required")
    @Size(min = 6, message = "The password must be at least 6 characters")
    private String password;

    public void validate() {
        if (!this.name.matches("^[A-Z\s]+$"))
            throw new BadRequestException("Tên in hoa không chứa dấu hay ký tự đặc biệt");

        if (!this.password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$"))
            throw new BadRequestException("Mật khẩu phải chứa ký tự hoa, ký tự thường và số");

    }
}
