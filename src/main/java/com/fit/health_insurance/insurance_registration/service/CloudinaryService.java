package com.fit.health_insurance.insurance_registration.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        try {
            return cloudinary.uploader()
                    .upload(file.getBytes(),
                            ObjectUtils.asMap("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
        }
        catch(RuntimeException ex) {
            throw new InterruptedIOException("Can not upload image");
        }
    }

    public void save (MultipartFile file) throws IOException {

    }
}
