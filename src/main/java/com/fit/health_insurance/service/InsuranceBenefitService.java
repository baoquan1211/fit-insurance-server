package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.InsuranceBenefitDto;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.repository.InsuranceBenefitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceBenefitService {
    private final InsuranceBenefitRepository repo;
    private final ModelMapper mapper;

    public InsuranceBenefitDto findById(Integer id) {
        var benefit = repo.findById(id).orElseThrow(() -> new NotFoundException("Benefit not found"));
        return mapper.map(benefit, InsuranceBenefitDto.class);
    }
}
