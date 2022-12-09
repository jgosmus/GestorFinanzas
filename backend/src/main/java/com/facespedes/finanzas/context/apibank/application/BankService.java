package com.facespedes.finanzas.context.apibank.application;

import com.facespedes.finanzas.context.apibank.domain.BankAPI;
import com.facespedes.finanzas.context.apibank.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BankService {

    private BankAPI bankApi;

    public List<Transaction> getData() {
        return bankApi.getTransactions();
    }
}
