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

    public List<InsuranceDto> findBySlug(String slug) {
        var entities = repo.findBySlug(slug);
        if (entities == null || entities.isEmpty())
            throw new NotFoundException("Insurance not found.");
        return entities.stream().map(this::convertToDto).toList();
    }

    public Integer calculateInsuranceFee(Integer id, CalculateFeeDto request) {
        var insurance = repo.findById(id).orElseThrow(() -> new NotFoundException("Insurance not found."));
        var today = LocalDate.now();
        var birthdate = LocalDate.parse(request.getBirthdate());
        var startDate = LocalDate.parse(request.getStartDate());
        if (startDate.isBefore(today) || startDate.isEqual(today)){
            throw new BadRequestException("Start date must be after today.");
        }
        var yearsBetween = birthdate.until(startDate, ChronoUnit.YEARS);

        if (yearsBetween < 30)
            return (insurance.getMinFeePerYear());
        if (yearsBetween < 40)
            return (int) (insurance.getMinFeePerYear() * 1.1);
        if (yearsBetween < 50)
            return (int) (insurance.getMinFeePerYear() * 1.2);
        if (yearsBetween < 70)
            return (int) (insurance.getMinFeePerYear() * 1.3);
        return (int) (insurance.getMinFeePerYear() * 1.4);
    }
}
