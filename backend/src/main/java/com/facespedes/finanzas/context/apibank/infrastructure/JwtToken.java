package com.facespedes.finanzas.context.apibank.infrastructure;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtToken {
    private String token;
}
