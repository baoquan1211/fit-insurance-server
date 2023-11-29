package com.fit.health_insurance.insurance.controller;

import com.fit.health_insurance.insurance.dto.InsuranceDto;
import com.fit.health_insurance.insurance.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/insurances")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService service;
    public ResponseEntity<List<InsuranceDto>> findAll() {
        return ResponseEntity.ok(service.finaAll());
    }

}
