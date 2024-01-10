package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.PayoutRequestCreationDto;
import com.fit.health_insurance.dto.PayoutRequestDto;
import com.fit.health_insurance.service.PayoutRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payout-request")
@RequiredArgsConstructor
public class PayoutRequestController {
    private final PayoutRequestService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#request.buyer == authentication.principal.username")
        PayoutRequestDto create(@Valid @ModelAttribute PayoutRequestCreationDto request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    PayoutRequestDto create(@PathVariable Integer id) {
        return service.findById(id);
    }
}
