package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.CalculateFeeDto;
import com.fit.health_insurance.dto.InsuranceDto;
import com.fit.health_insurance.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insurances")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService service;
    @GetMapping
    public ResponseEntity<List<InsuranceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/insurance-types/slug/{slug}")
    public ResponseEntity<List<InsuranceDto>> findBySlug(@PathVariable("slug") String slug) {
        return ResponseEntity.ok(service.findBySlug(slug));
    }
    @GetMapping("/{id}")
    public ResponseEntity<InsuranceDto> findBySlug(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping("/{id}/calculate-fee")
    public ResponseEntity<Integer> calculateInsuranceFee(@PathVariable("id") Integer id, @RequestBody @Valid CalculateFeeDto request) {
        return ResponseEntity.ok(service.calculateInsuranceFee(id,request.getBirthdate(), request.getStartDate()));
    }
}
