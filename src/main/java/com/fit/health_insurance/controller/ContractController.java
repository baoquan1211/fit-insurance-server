package com.fit.health_insurance.controller;

import com.fit.health_insurance.dto.ContractCreationDto;
import com.fit.health_insurance.dto.ContractDto;
import com.fit.health_insurance.service.ContractService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService service;
    @Value("${application.client.payment-return-url}")
    private String CLIENT_PAYMENT_RETURN_URL;

    @PreAuthorize("#request.buyer  == authentication.principal.username")
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public ContractDto create(@Valid @RequestBody ContractCreationDto request) {
        return service.create(request);
    }

    @PreAuthorize("#email == authentication.principal.username")
    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping
    public List<ContractDto> findByEmail(@PathParam("email") String email) {
        return service.findByEmail(email);
    }

    @PostAuthorize("returnObject.buyer.email == authentication.principal.username")
    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping("/{id}")
    public ContractDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping("/{id}/vnpay")
    public String getPaymentUrl(@PathVariable Integer id) {
        return service.getVnPayUrl(id);
    }

    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping("/vnpay-payment")
    public RedirectView paymentCheck(HttpServletRequest request) {
        var vnp_OrderInfo = request.getParameter("vnp_OrderInfo").split("_");
        Integer contractId = Integer.parseInt(vnp_OrderInfo[0].trim());
        Integer paymentId = Integer.parseInt(vnp_OrderInfo[1].trim());
        service.paymentCheck(contractId, paymentId, request);
        return new RedirectView(CLIENT_PAYMENT_RETURN_URL + contractId);
    }
}
