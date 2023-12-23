package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.DistrictDto;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository  districtRepository;
    private final ProvinceService provinceService;
    private final ModelMapper mapper;

    public List<DistrictDto> findAllByProvinceId(Integer id) {
        provinceService.findById(id);
        var districts = districtRepository.findAllByProvinceId(id);
        if (districts.isEmpty()) {
            throw new NotFoundException("District not found");
        }
        return districts.stream().map(district -> mapper.map(district, DistrictDto.class)).toList();
    }
}
