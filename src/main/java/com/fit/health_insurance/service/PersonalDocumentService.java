package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.PersonalDocumentResponseDto;
import com.fit.health_insurance.model.PersonalDocument;
import com.fit.health_insurance.repository.PersonalDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDocumentService {
    private final CloudinaryService cloudinaryService;
    private final PersonalDocumentRepository personalDocumentRepository;

    public List<PersonalDocumentResponseDto> findByRegistrationForm(Integer formID) {
        return personalDocumentRepository.findByRegistrationForm(formID).stream().map(this::convertToDto).toList();
    }

    public Optional<PersonalDocument> findById(Integer id) {
        return personalDocumentRepository.findById(id);
    }
    public void upload(PersonalDocument personalDocument, MultipartFile file) throws IOException {
        var imageUrl = cloudinaryService.uploadImage(file);
        personalDocument.setURL(imageUrl);
        personalDocumentRepository.save(personalDocument);
    }

    public PersonalDocumentResponseDto convertToDto(PersonalDocument entity) {
        return PersonalDocumentResponseDto.builder()
                .URL(entity.getURL())
                .name(entity.getName())
                .build();
    }
    public void save(PersonalDocument personalDocument) {
        personalDocumentRepository.save(personalDocument);
    }
}
