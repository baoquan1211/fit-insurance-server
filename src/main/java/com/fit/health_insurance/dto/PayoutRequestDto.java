package com.fit.health_insurance.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutRequestDto {
    private Integer id;
    private Date createdAt;
    private String status;
    private Set<InsuranceBenefitDto> benefits;
    private Integer contractId;
    private Integer totalPay;
    private List<HealthDocumentResponseDto> documents;
}
