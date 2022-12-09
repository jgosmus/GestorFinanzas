package com.facespedes.finanzas.context.apibank.domain;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public final class Money {
    private double amount;
    private String currency;
}
