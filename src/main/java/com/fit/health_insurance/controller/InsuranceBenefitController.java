package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.InsuranceBenefitDto;
import com.fit.health_insurance.service.InsuranceBenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benefits")
@RequiredArgsConstructor
public class InsuranceBenefitController {
    private final InsuranceBenefitService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InsuranceBenefitDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/insurances/{insuranceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceBenefitDto> findByInsurance(@PathVariable Integer insuranceId, @RequestParam(defaultValue = "all", required = false) String status) {
        return service.findByInsurance(insuranceId, status);
    }

    @GetMapping("/contracts/{contractId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceBenefitDto> findByContract(@PathVariable Integer contractId) {
        return service.findByContract(contractId);
    }
}
