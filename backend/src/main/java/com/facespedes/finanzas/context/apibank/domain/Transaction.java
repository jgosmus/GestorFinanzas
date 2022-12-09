package com.facespedes.finanzas.context.apibank.domain;

import java.time.LocalDate;

public record Transaction(String internalTransactionId, LocalDate valueDate, Money amount, String concept) {
}
