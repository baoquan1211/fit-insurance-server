package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.InsuranceTypeDto;
import com.fit.health_insurance.service.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insurance-types")
@RequiredArgsConstructor
public class InsuranceTypeController {
    private final InsuranceTypeService service;
    @GetMapping("/slug/{slug}")
    public ResponseEntity<InsuranceTypeDto> findBySlug(@PathVariable("slug") String slug) {
        return ResponseEntity.ok(service.findBySlug(slug));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceTypeDto> findBySlug(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<InsuranceTypeDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
