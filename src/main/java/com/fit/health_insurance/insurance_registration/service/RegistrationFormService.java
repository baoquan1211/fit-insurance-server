package com.fit.health_insurance.insurance_registration.service;

import com.fit.health_insurance.exception.InternalErrorException;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.insurance_registration.dto.RegistrationFormRequestDto;
import com.fit.health_insurance.insurance_registration.dto.RegistrationFormResponseDto;
import com.fit.health_insurance.insurance_registration.model.PersonalDocument;
import com.fit.health_insurance.insurance_registration.model.RegistrationForm;
import com.fit.health_insurance.insurance_registration.model.RegistrationStatus;
import com.fit.health_insurance.insurance_registration.repository.RegistrationRepository;
import com.fit.health_insurance.user.model.User;
import com.fit.health_insurance.user.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RegistrationFormService {
    private final RegistrationRepository registrationRepository;
    private final PersonalDocumentService personalDocumentService;
    private final UserDetailsService userService;

    private RegistrationFormResponseDto convertToDto(RegistrationForm entity) {
        return RegistrationFormResponseDto.builder()
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .birthday(entity.getBirthday())
                .name(entity.getName())
                .identityCard(entity.getIdentityCard())
                .files(personalDocumentService.findByRegistrationForm(entity.getId()))
                .build();
    }

    public List<RegistrationFormResponseDto> findAll() {
        var antiHeroList = registrationRepository.findAll();
        return antiHeroList.stream().map(this::convertToDto).toList();
    }


    public RegistrationFormResponseDto findById(Integer id) {
        if (registrationRepository.findById(id).isPresent()) {
            var entity = registrationRepository.findById(id).get();
            return convertToDto(entity);
        }
        else throw new NotFoundException("Registration form not found");
    }


    public void create(RegistrationFormRequestDto request) {
        var user = userService.loadUserByUsername(request.getEmail());
        if (user != null) {
            RegistrationForm formEntity =  RegistrationForm.builder()
                    .birthday(LocalDate.parse(request.getBirthday()))
                    .address(request.getAddress())
                    .registrator((User) user)
                    .address(request.getAddress())
                    .name(request.getName())
                    .phone(request.getPhone())
                    .status(RegistrationStatus.PENDING)
                    .createdAt(new Date())
                    .build();
            var documentList = request.getFile();
            for (MultipartFile image : documentList) {
                try {
                    var documentEntity = new PersonalDocument(formEntity);
                    personalDocumentService.upload(documentEntity, image);
                    personalDocumentService.save(documentEntity);
                } catch (IOException e) {
                    throw new InternalErrorException("Please try again");
                }
            }
            registrationRepository.save(formEntity);
        }
    }
}
