package com.facespedes.finanzas.context.apibank.application;

import com.facespedes.finanzas.context.apibank.domain.BankAPI;
import com.facespedes.finanzas.context.apibank.domain.Transaction;
import com.facespedes.finanzas.context.apibank.domain.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BankService {

    private final BankAPI bankApi;
    private final TransactionRepository transactionRepository;
    private LocalDateTime lastUpdate;


    public List<Transaction> getTransactions(Optional<LocalDate> from) {
        if(transactionsNeedsUpdate())
            updateTransactions();

        if(from.isPresent())
            return transactionRepository.findAllBefore(from.get());
        else
            return transactionRepository.findAll();

    }

    private boolean transactionsNeedsUpdate() {
        int hoursToUpdate = 1;
        return lastUpdate == null || lastUpdate.isBefore(LocalDateTime.now().minusHours(hoursToUpdate));
    }

    private void updateTransactions() {
        lastUpdate = LocalDateTime.now();

        List<Transaction> newTransactions = getNewTransactionsFromApi();
        this.transactionRepository.saveAll(newTransactions);

    }

    private List<Transaction> getNewTransactionsFromApi() {
        List<Transaction> transactionsFromBd = transactionRepository.findAll();
        List<Transaction> transactionsFromApi = bankApi.getTransactions();
        return transactionsFromApi.stream()
                .filter(transaction -> !transactionsFromBd.contains(transaction)).toList();
    }

}
