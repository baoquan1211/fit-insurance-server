package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.InsuranceBenefitDto;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.model.InsuranceBenefit;
import com.fit.health_insurance.repository.InsuranceBenefitRepository;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceBenefitService {
    private final InsuranceBenefitRepository benefitRepository;
    private final ContractService contractService;
    private final ModelMapper mapper;

    public InsuranceBenefitDto findById(Integer id) {
        var benefit = benefitRepository.findById(id).orElseThrow(() -> new NotFoundException("Benefit not found"));
        return mapper.map(benefit, InsuranceBenefitDto.class);
    }

    public List<InsuranceBenefitDto> findByInsurance(Integer insuranceId, String status) {
        List<InsuranceBenefit> benefits;
        switch (status) {
            case "active" -> benefits = benefitRepository.findActiveByInsurance(insuranceId);
            case "inactive" -> benefits = benefitRepository.findInActiveByInsurance(insuranceId);
            default -> benefits = benefitRepository.findAllByInsurance(insuranceId);
        }
        if (benefits == null || benefits.isEmpty()) {
            throw new NotFoundException("Benefits not found");
        }
        return benefits.stream().map(benefit -> mapper.map(benefit, InsuranceBenefitDto.class)).sorted(Comparator.comparingInt(InsuranceBenefitDto::getAmount)).toList();
    }

    public List<InsuranceBenefitDto> findByContract(Integer contractId) {
        var contract = contractService.findById(contractId);
        var benefitsId = new JSONArray(contract.getBenefitsId());
        List<InsuranceBenefitDto> benefits  = new ArrayList<>();
        for (int i = 0; i < benefitsId.length(); i++) {
            var benefit = benefitRepository.findById((int) benefitsId.get(i)).orElseThrow(() -> new NotFoundException("Benefit not found"));
            benefits.add(mapper.map(benefit, InsuranceBenefitDto.class));
        }
        return benefits;
    }
}
