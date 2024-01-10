package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.PayoutRequestCreationDto;
import com.fit.health_insurance.dto.PayoutRequestDto;
import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.enums.PayoutRequestStatus;
import com.fit.health_insurance.exception.BadRequestException;
import com.fit.health_insurance.exception.InternalErrorException;
import com.fit.health_insurance.model.Contract;
import com.fit.health_insurance.model.InsuranceBenefit;
import com.fit.health_insurance.model.PayoutRequest;
import com.fit.health_insurance.repository.PayoutRequestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PayoutRequestService {
    private final PayoutRequestRepository payoutRequestRepository;
    private final ModelMapper mapper;
    private final InsuranceBenefitService insuranceBenefitService;
    private final ContractService contractService;

    public PayoutRequestDto create(PayoutRequestCreationDto request) {
        var contract = contractService.findById(request.getContract());
        if (!Objects.equals(contract.getStatus(), ContractStatus.ACTIVE.toString())) {
            throw new BadRequestException("Contract is not active");
        }
        if (!Objects.equals(contract.getBuyer().getEmail(), request.getBuyer())) {
            throw new BadRequestException("Buyer email is not valid");
        }
        Set<Integer> benefitsId = request.getBenefits();
        Set<InsuranceBenefit> benefits = new HashSet<InsuranceBenefit>(Set.of());
        Integer totalPay = 0;
        for (Integer benefitId : benefitsId) {
            var benefit = insuranceBenefitService.findById(benefitId);
            if (!Objects.equals(benefit.getInsuranceId(), contract.getInsurance().getId())) {
                throw new BadRequestException("Benefit is not belong to this contract");
            }
            benefits.add(mapper.map(benefit, InsuranceBenefit.class));
            totalPay += benefit.getAmount();
        }
        PayoutRequest payoutRequest = PayoutRequest.builder()
                .contract(mapper.map(contract, Contract.class))
                .benefits(benefits)
                .totalPay(totalPay)
                .status(PayoutRequestStatus.PENDING)
                .createdAt(new Date())
                .build();
        try {
            payoutRequestRepository.save(payoutRequest);
        } catch (RepositoryCreationException ex) {
            throw new InternalErrorException(ex.getMessage());
        }
        return mapper.map(payoutRequest, PayoutRequestDto.class);
    }
}
