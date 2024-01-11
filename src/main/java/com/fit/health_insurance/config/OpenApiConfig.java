package com.fit.health_insurance.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.app-version}") String appVersion) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .description("Auth by JWT access token, please login to get a token.")
                                        .type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt"))
                .info(new Info()
                        .title("Fit Insurance").version(appVersion)
                        .contact(new Contact()
                                .name("Quach Bao Quan")
                                .url("https://quan-qb.vercel.app/")
                                .email("quachbaoquan123@gmail.com")
                        )
                        .description("Web API for Fit Insurance")
                );
    }
}
