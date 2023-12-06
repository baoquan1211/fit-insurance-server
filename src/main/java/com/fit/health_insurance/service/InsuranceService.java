package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.InsuranceDto;
import com.fit.health_insurance.model.Insurance;
import com.fit.health_insurance.repository.InsuranceReposity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceReposity insuranceReposity;
    private ModelMapper mapper;
    private InsuranceDto convertToDto(Insurance entity) {
        return mapper.map(entity, InsuranceDto.class);
    }

    public List<InsuranceDto> finaAll() {
        return insuranceReposity.findAll().stream().map(this::convertToDto).toList();
    }
}
