package com.facespedes.finanzas.context.apibank.infrastructure.nordigen;

import com.facespedes.finanzas.context.apibank.domain.BankAPI;
import com.facespedes.finanzas.context.apibank.domain.Money;
import com.facespedes.finanzas.context.apibank.domain.Transaction;
import com.facespedes.finanzas.context.apibank.infrastructure.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BankApiNordigen implements BankAPI {

    private final BankApiClientNordigen bankApiClient;
    private final ObjectMapper objectMapper;
    private final JwtToken jwtToken;

    @Value("${nordigen.secret.id}")
    private String nordigenSecretId;

    @Value("${nordigen.secret.key}")
    private String nordigenSecretKey;

    @Value("${nordigen.account}")
    private String nordigenAccount;

    @Override
    public List<Transaction> getTransactions() {
        Map<Object, Object> response = getBankApiClientTransactions();
        List<Map<String, Object>> transactionsFromResponse = getTransactionsFromResponse(response);

        return getDomainTransactionsFromTransactionsResponse(transactionsFromResponse);
    }

    private List<Transaction> getDomainTransactionsFromTransactionsResponse(List<Map<String, Object>> bookedTransactions) {
        return bookedTransactions.stream().map(apiTransaction -> {
            Map<String, Object> amount = this.objectMapper.convertValue(apiTransaction.get("transactionAmount"), Map.class);
            return new Transaction(
                    (String) apiTransaction.get("internalTransactionId"),
                    LocalDate.parse((String) apiTransaction.get("bookingDate")),
                    new Money(Double.parseDouble((String) amount.get("amount")), (String) amount.get("currency")),
                    (String) apiTransaction.get("remittanceInformationUnstructured")
            );
        }).toList();
    }

    private List<Map<String, Object>> getTransactionsFromResponse(Map<Object, Object> transactionsResponse) {
        Map<String, Object> transactions = this.objectMapper
                .convertValue(transactionsResponse.get("transactions"), Map.class);
        return this.objectMapper.convertValue(transactions.get("booked"), List.class);
    }

    private Map<Object, Object> getBankApiClientTransactions() {
        BankApiClientNordigenCredentials credentials = new BankApiClientNordigenCredentials(this.nordigenSecretId,
                this.nordigenSecretKey);
        Map<String, String> authenticate = this.bankApiClient.authenticate(credentials);
        this.jwtToken.setToken(authenticate.get("access"));
        return this.bankApiClient.getTransactions(this.nordigenAccount);
    }
}
