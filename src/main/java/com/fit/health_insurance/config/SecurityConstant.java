package com.fit.health_insurance.config;


public final class SecurityConstant {
    public static final String[] WHITE_LIST_URLS = {
            // api documentation
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/v3/api-docs/**",
            // auth endpoint
            "/api/v1/login",
            "/api/v1/register",
            "/api/v1/refresh",
            "/api/v1/logout",
            // public endpoint
            "/api/v1/insurances/**",
            "/api/v1/insurance-types/**",
            "/api/v1/provinces/**",
            "/api/v1/payment/**",
            "/api/v1/contracts/vnpay-payment"
    };
}
