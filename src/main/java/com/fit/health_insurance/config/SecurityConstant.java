package com.fit.health_insurance.config;


public final class SecurityConstant {
    public static final String[] WHITE_LIST_URLS = {
            "/api/v1/login",
            "/api/v1/register",
            "/api/v1/refresh",
            "/api/v1/logout",
            "/api/v1/insurances/**",
            "/api/v1/insurance-types/**",
            "/api/v1/provinces/**",
    };
}
