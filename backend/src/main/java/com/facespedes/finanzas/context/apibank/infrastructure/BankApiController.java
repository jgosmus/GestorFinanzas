package com.facespedes.finanzas.context.apibank.infrastructure;

import com.facespedes.finanzas.context.apibank.application.BankService;
import com.facespedes.finanzas.context.apibank.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("api/v1")
@RestController
public class BankApiController {

    private BankService bankService;

    @GetMapping(value = {"transactions", "transactions/{from}"})
    public List<Transaction> getTransactions(@PathVariable Optional<LocalDate> from) {
        return this.bankService.getTransactions(from);
    }
}
