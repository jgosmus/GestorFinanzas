package com.facespedes.finanzas.context.apibank.infrastructure;

import feign.RequestInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BankFeignClientConfiguration {
    private JwtToken jwtToken;

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        SecurityContextHolder.getContext().getAuthentication();
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + jwtToken.getToken());
    }
}
