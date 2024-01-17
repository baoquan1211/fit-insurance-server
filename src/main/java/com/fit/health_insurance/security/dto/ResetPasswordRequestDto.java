package com.fit.health_insurance.security.dto;

import com.fit.health_insurance.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequestDto {
    @NotEmpty(message = "The email is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotEmpty(message = "The last password is required")
    private String lastPassword;
    @Size(min = 6, message = "The password must be at least 6 characters")
    @NotEmpty(message = "The new password is required")
    private String newPassword;

    public void validate() {
        if (!this.newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$"))
            throw new BadRequestException("Mật khẩu phải chứa ký tự hoa, ký tự thường và số");

    }
}
