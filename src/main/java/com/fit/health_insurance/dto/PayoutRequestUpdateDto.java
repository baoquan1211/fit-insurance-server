package com.fit.health_insurance.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayoutRequestUpdateDto {
    private String message;
    @NotEmpty(message = "Status is required")
    private String status;
}
