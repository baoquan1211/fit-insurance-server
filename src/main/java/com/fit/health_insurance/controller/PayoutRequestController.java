package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.PageableResponseDto;
import com.fit.health_insurance.dto.PayoutRequestCreationDto;
import com.fit.health_insurance.dto.PayoutRequestDto;
import com.fit.health_insurance.dto.PayoutRequestUpdateDto;
import com.fit.health_insurance.service.PayoutRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payout-requests")
@RequiredArgsConstructor
public class PayoutRequestController {
    private final PayoutRequestService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
        public PageableResponseDto<PayoutRequestDto> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer limit ) {
        return service.findAll(page, limit);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#request.buyer == authentication.principal.username")
    public PayoutRequestDto create(@Valid @ModelAttribute PayoutRequestCreationDto request) {
        return service.create(request);
    }

    @PreAuthorize("#email == authentication.principal.username")
    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping("/email/{email}")
    public List<PayoutRequestDto> findByEmail(@PathVariable String email, @RequestParam(defaultValue = "all", required = false) String status) {
        return service.findByEmail(email, status);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PayoutRequestDto create(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@PathVariable Integer id, @Valid @RequestBody PayoutRequestUpdateDto request) {
        service.updateStatus(id, request.getStatus().toUpperCase(), request.getMessage());
    }

}
