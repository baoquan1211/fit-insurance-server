package com.fit.health_insurance.service;

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

    public String upload(MultipartFile file) throws IOException {
        try {
            return cloudinary.uploader()
                    .upload(file.getBytes(),
                            ObjectUtils.asMap("public_id", "fit-insurance/" + UUID.randomUUID()))
                    .get("url")
                    .toString();
        }
        catch(RuntimeException ex) {
            throw new InterruptedIOException("Can not upload image.");
        }
    }

    private String getIdFromUrl(String url) {
        int startIndex = url.lastIndexOf("/") + 1;
        int UUID_LENGTH = 36;
        return url.substring(startIndex, startIndex + UUID_LENGTH);
    }

    public void delete(String url) throws IOException {
        try {
            String publicId = getIdFromUrl(url);
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("type", "upload", "resource_type", "image", "invalidate", "true"));
        }
        catch(RuntimeException ex) {
            throw new InterruptedIOException("Can not delete image.");
        }
    }

}
