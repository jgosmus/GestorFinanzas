package com.facespedes.finanzas.context.apibank.domain;

import java.util.List;

public interface BankAPI {
    List<Transaction> getTransactions();
}
