package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.DistrictDto;
import com.fit.health_insurance.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/districts")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService service;

    @GetMapping("/provinces/{id}")
    public ResponseEntity<List<DistrictDto>> findAllByProvinceId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findAllByProvinceId(id));
    }
}
