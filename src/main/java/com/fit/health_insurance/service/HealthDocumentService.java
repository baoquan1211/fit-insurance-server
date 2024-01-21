package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.HealthDocumentResponseDto;
import com.fit.health_insurance.model.HealthDocument;
import com.fit.health_insurance.repository.HealthDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthDocumentService {
    private final CloudinaryService cloudinaryService;
    private final HealthDocumentRepository healthDocumentRepository;

    public List<HealthDocumentResponseDto> findByRegistrationForm(Integer formID) {
        return healthDocumentRepository.findByRegistrationForm(formID).stream().map(this::convertToDto).toList();
    }

    public Optional<HealthDocument> findById(Integer id) {
        return healthDocumentRepository.findById(id);
    }
    public void upload(HealthDocument healthDocument, MultipartFile file) throws IOException {
        var imageUrl = cloudinaryService.upload(file);
        healthDocument.setURL(imageUrl);
    }

    public HealthDocumentResponseDto convertToDto(HealthDocument entity) {
        return HealthDocumentResponseDto.builder()
                .url(entity.getURL())
                .name(entity.getName())
                .build();
    }
    public void save(HealthDocument healthDocument) {
        healthDocumentRepository.save(healthDocument);
    }
}
