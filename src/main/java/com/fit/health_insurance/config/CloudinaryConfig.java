package com.fit.health_insurance.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Value("${application.cloudinary.cloud-name}")
    private String CLOUD_NAME;
    @Value("${application.cloudinary.api-key}")
    private String API_KEY;
    @Value("${application.cloudinary.secret-key}")
    private String SECRET_KEY;

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", SECRET_KEY);

        return new Cloudinary(config);
    }
}

