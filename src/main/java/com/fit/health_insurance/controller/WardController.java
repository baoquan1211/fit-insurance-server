package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.WardDto;
import com.fit.health_insurance.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wards")
@RequiredArgsConstructor
public class WardController {
    private final WardService service;

    @GetMapping("/districts/{id}")
    public ResponseEntity<List<WardDto>>findAllByDistrictId (@PathVariable Integer id) {
        return ResponseEntity.ok(service.findAllByDistrictId(id));
    }
}
