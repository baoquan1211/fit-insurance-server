package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.RegistrationFormCreationDto;
import com.fit.health_insurance.dto.RegistrationFormResponseDto;
import com.fit.health_insurance.service.RegistrationFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationFormController {
    private final RegistrationFormService registrationFormService;
    @PreAuthorize("#email == authentication.principal.username")
    @GetMapping("/users/email/{email}")
    public ResponseEntity<List<RegistrationFormResponseDto>> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(registrationFormService.findByEmail(email));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<RegistrationFormResponseDto>> findAll() {
        return ResponseEntity.ok(registrationFormService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationFormResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(registrationFormService.findById(id));
    }

    @PreAuthorize("#request.email == authentication.principal.username")
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public void create(@Valid @ModelAttribute RegistrationFormCreationDto request) {
        registrationFormService.create(request);
    }
}
