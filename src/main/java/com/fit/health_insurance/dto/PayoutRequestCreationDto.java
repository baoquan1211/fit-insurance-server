package com.fit.health_insurance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutRequestCreationDto implements Serializable {
    @NotEmpty(message = "The benefits are required")
    private Set<Integer> benefits;
    @NotNull(message = "The contract id are required")
    private Integer contract;
    @Email(message = "The buyer email is not valid")
    private String buyer;
    @NotNull(message = "The file must be provided at least one")
    List<MultipartFile> files;
}
