package com.fit.health_insurance.dto;

import com.fit.health_insurance.model.Insurance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceTypeDto {
    private String name;
    private String slug;
    private String description;
    private boolean isActive;
    private String image;
}