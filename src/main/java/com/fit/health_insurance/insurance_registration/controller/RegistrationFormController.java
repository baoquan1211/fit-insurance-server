package com.fit.health_insurance.insurance_registration.controller;

import com.fit.health_insurance.insurance_registration.dto.RegistrationFormRequestDto;
import com.fit.health_insurance.insurance_registration.dto.RegistrationFormResponseDto;
import com.fit.health_insurance.insurance_registration.service.RegistrationFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationFormController {
    private final RegistrationFormService registrationFormService;

    @GetMapping
    public ResponseEntity<List<RegistrationFormResponseDto>> getAll() {
        return ResponseEntity.ok(registrationFormService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationFormResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(registrationFormService.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public void create(@Valid @ModelAttribute RegistrationFormRequestDto request) {
        registrationFormService.create(request);
    }
}
