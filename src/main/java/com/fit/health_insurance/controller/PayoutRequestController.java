package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.PayoutRequestCreationDto;
import com.fit.health_insurance.dto.PayoutRequestDto;
import com.fit.health_insurance.service.PayoutRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payout-request")
@RequiredArgsConstructor
public class PayoutRequestController {
    private final PayoutRequestService service;

    @PostMapping
    @PreAuthorize("#request.buyer == authentication.principal.username")
    PayoutRequestDto create(@Valid @RequestBody PayoutRequestCreationDto request) {
        return service.create(request);
    }
}
