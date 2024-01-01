package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.WardDto;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WardService {
    private final WardRepository repo;
    private final ModelMapper mapper;

    public List<WardDto> findAllByDistrictId(Integer id) {
        var wards = repo.findAllByDistrictId(id);
        if (wards.isEmpty()) {
            throw new NotFoundException("Ward not found");
        }
        return wards.stream().map(ward -> mapper.map(ward, WardDto.class)).toList();
    }

    public WardDto findById(Integer id) {
        var ward = repo.findById(id).orElseThrow(() -> new NotFoundException("Ward not found"));
        return mapper.map(ward, WardDto.class);
    }
}
