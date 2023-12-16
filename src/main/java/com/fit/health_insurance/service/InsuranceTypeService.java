package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.InsuranceTypeDto;
import com.fit.health_insurance.repository.InsuranceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceTypeService {
    private final InsuranceTypeRepository repo;
    private final ModelMapper mapper;
    public InsuranceTypeDto findBySlug(String slug) {
        var entity = repo.findBySlug(slug);
        return mapper.map(entity, InsuranceTypeDto.class);
    }

    public List<InsuranceTypeDto> findAll() {
        var entities = repo.findAll();
        return entities.stream().map(entity -> mapper.map(entity, InsuranceTypeDto.class)).toList();
    }
}
