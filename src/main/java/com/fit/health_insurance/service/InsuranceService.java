package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.CalculateFeeDto;
import com.fit.health_insurance.dto.InsuranceDto;
import com.fit.health_insurance.exception.BadRequestException;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.model.Insurance;
import com.fit.health_insurance.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository repo;
    private final ModelMapper mapper;
    private InsuranceDto convertToDto(Insurance entity) {
        return mapper.map(entity, InsuranceDto.class);
    }

    public List<InsuranceDto> findAll() {
        return repo.findAll().stream().map(this::convertToDto).toList();
    }

    public InsuranceDto findById(Integer id) {
        var entity = repo.findById(id).orElseThrow(() -> new NotFoundException("Insurance not found."));
        var dto = convertToDto(entity);
        dto.setInsuranceTypeId(entity.getInsuranceType().getId());
        return dto;
    }


    public List<InsuranceDto> findBySlug(String slug) {
        var entities = repo.findBySlug(slug);
        if (entities == null || entities.isEmpty())
            throw new NotFoundException("Insurance not found");
        return entities.stream().map(this::convertToDto).toList();
    }

    private Integer calculateFeeByAge(Long age, Insurance insurance) {
        final Map<Integer, Double> feeByAge = Map.of(
                30, 1.0,
                40, 1.1,
                50, 1.2,
                60, 1.25,
                70, 1.3,
                80, 1.4
        );
        for (Map.Entry<Integer, Double> entry : feeByAge.entrySet()) {
            Integer minAge = entry.getKey();
            Double rate = entry.getValue();
            if (age < minAge) {
                return (int) (insurance.getMinFeePerYear() * rate);
            }
        }
        return -1;
    }

    public Integer calculateInsuranceFee(Integer id, String birthdateRequest,  String startDateRequest) {
        var insurance = repo.findById(id).orElseThrow(() -> new NotFoundException("Insurance not found"));
        var today = LocalDate.now();
        var birthdate = LocalDate.parse(birthdateRequest);
        var startDate = LocalDate.parse(startDateRequest);

        if (startDate.isBefore(today) || startDate.isEqual(today)){
            throw new BadRequestException("Start date must be after today");
        }

        var userAge = birthdate.until(startDate, ChronoUnit.YEARS);
        var fee = calculateFeeByAge(userAge, insurance);

        if (fee < 0) {
            throw new BadRequestException("Not allowed to buy this insurance");
        }
        return fee;
    }
}
