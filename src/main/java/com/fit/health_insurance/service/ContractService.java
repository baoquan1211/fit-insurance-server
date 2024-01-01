package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.ContractCreationDto;
import com.fit.health_insurance.dto.ContractDto;
import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.enums.Gender;
import com.fit.health_insurance.exception.BadRequestException;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.model.Contract;
import com.fit.health_insurance.model.Insurance;
import com.fit.health_insurance.model.User;
import com.fit.health_insurance.repository.ContractRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final UserService userService;
    private final ProvinceService provinceService;
    private final DistrictService districtService;
    private final WardService WardService;
    private final ContractRepository contractRepository;
    private final ModelMapper mapper;
    private final InsuranceService insuranceService;
    private final PaymentService paymentService;

    public List<ContractDto> findByEmail(String email) {
        var contracts = contractRepository.findAllByEmail(email);
        if (contracts.isEmpty())
            throw new NotFoundException("No contract found for email " + email);
        return contracts.stream().map(contract -> mapper.map(contract, ContractDto.class)).toList();
    }

    public ContractDto findById(Integer id) {
        var contract = contractRepository.findById(id).orElseThrow(() -> new NotFoundException("Contract not found"));
        return mapper.map(contract, ContractDto.class);
    }

    public void updateStatus(Integer id, ContractStatus status) {
        var contract = contractRepository.findById(id).orElseThrow(() -> new NotFoundException("Contract not found"));
        contract.setStatus(status);
        contractRepository.save((contract));
    }

    public ContractDto create(ContractCreationDto request) {
        var buyer = userService.loadUserByUsername(request.getBuyer());
        var gender = Gender.valueOf(request.getGender().toUpperCase());
        var provinceDto = provinceService.findById(request.getProvince());
        var districtDto = districtService.findById(request.getDistrict());
        var wardDto = WardService.findById(request.getWard());
        var address = request.getStreet() + ", " + wardDto.getType() + " " + wardDto.getName() + ", " + districtDto.getType() + " " + districtDto.getName() + ", " + provinceDto.getType() + " " + provinceDto.getName();
        var insuranceDto = insuranceService.findById(request.getInsurance());
        var createdAt = new Date();


        if (buyer != null) {
            Contract contractEntity = Contract.builder()
                    .buyer((User) buyer)
                    // Insured person information
                    .name(request.getName())
                    .gender(gender)
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .birthdate(LocalDate.parse(request.getBirthdate()))
                    .identityCard(request.getIdentityCard())
                    .wardId(wardDto.getId())
                    .address(address)

                    // Insurance information
                    .insurance(mapper.map(insuranceDto, Insurance.class))
                    .price(insuranceService.calculateInsuranceFee(request.getInsurance(), request.getBirthdate(), request.getStartAt()))
                    .totalPayPerYear(insuranceDto.getTotalPayPerYear())
                    .inpatientFeePayPerDay(insuranceDto.getInpatientFeePayPerDay())
                    .healthCheckFeePayBeforeInpatientPerYear(insuranceDto.getHealthCheckFeePayBeforeInpatientPerYear())
                    .healthCheckFeePayAfterInpatientPerYear(insuranceDto.getHealthCheckFeePayAfterInpatientPerYear())
                    .surgicalFeePayPerYear(insuranceDto.getSurgicalFeePayPerYear())
                    .medicalVehicleFeePayPerYear(insuranceDto.getMedicalVehicleFeePayPerYear())
                    .functionalRestorationPayPerYear(insuranceDto.getFunctionalRestorationPayPerYear())

                    // Contract information
                    .startAt(LocalDate.parse(request.getStartAt()))
                    .endAt(LocalDate.parse(request.getStartAt()).plusYears(1))
                    .status(ContractStatus.INITIAL)
                    .createdAt(createdAt)
                    .build();

            contractRepository.save(contractEntity);
            return mapper.map(contractEntity, ContractDto.class);
        } else throw new NotFoundException("User not found");
    }

    public String getVnPayUrl(Integer id) {
        var contract = contractRepository.findById(id).orElseThrow(() -> new NotFoundException("Contract not found"));
        if (contract.getStatus() == ContractStatus.ACTIVE || contract.getStatus() == ContractStatus.EXPIRED)
            throw new BadRequestException("Contract have been paid or expired");
        if (contract.getStatus() == ContractStatus.INITIAL) {
            contract.setStatus(ContractStatus.UNPAID);
            contractRepository.save(contract);
        }
        return paymentService.vnPayCreateUrl(contract);
    }

    public void paymentCheck(Integer contractId, Integer paymentId, HttpServletRequest request) {
        var paymentStatus = paymentService.paymentCheck(paymentId, request);
        if (paymentStatus == 1) {
            updateStatus(contractId, ContractStatus.ACTIVE);
        }
    }

}
