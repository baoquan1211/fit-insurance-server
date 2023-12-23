package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.ProvinceDto;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository repo;
    private final ModelMapper mapper;

    public List<ProvinceDto> findAll() {
        var provinces =  repo.findAll().stream().map(province -> mapper.map(province, ProvinceDto.class)).toList();
        if (provinces.isEmpty()) {
            throw new NotFoundException("Province not found");
            
        }
        return provinces;
    }

    public ProvinceDto findById(Integer id) {
        var province = repo.findById(id).orElseThrow(() -> new NotFoundException("Province not found"));
        return mapper.map(province, ProvinceDto.class);
    }
}
