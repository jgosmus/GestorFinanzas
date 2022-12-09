package com.facespedes.finanzas.context.apibank.infrastructure;

import com.facespedes.finanzas.context.apibank.application.BankService;
import com.facespedes.finanzas.context.apibank.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/")
@RestController
public class BankApiController {

    private BankService bankService;

    @GetMapping
    public List<Transaction> getTransactions() {
        return this.bankService.getTransactions();
    }
}
