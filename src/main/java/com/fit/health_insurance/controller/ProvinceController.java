package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.InsuranceTypeDto;
import com.fit.health_insurance.dto.ProvinceDto;
import com.fit.health_insurance.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService service;

    @GetMapping
    public ResponseEntity<List<ProvinceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
