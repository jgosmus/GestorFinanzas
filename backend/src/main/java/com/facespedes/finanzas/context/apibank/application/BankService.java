package com.facespedes.finanzas.context.apibank.application;

import com.facespedes.finanzas.context.apibank.domain.BankAPI;
import com.facespedes.finanzas.context.apibank.domain.Transaction;
import com.facespedes.finanzas.context.apibank.domain.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BankService {

    private BankAPI bankApi;
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = bankApi.getTransactions();
        List<Transaction> transactionsFromBd = transactionRepository.findAll();
        List<Transaction> newTransactions = transactions.stream()
                .filter(transaction -> !transactionsFromBd.contains(transaction)).toList();
        this.transactionRepository.saveAll(newTransactions);

        ArrayList<Transaction> allTransactions = new ArrayList<>(transactions);
        allTransactions.addAll(newTransactions);

        return allTransactions;
    }
}
