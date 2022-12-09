package com.facespedes.finanzas.context.apibank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public final class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String internalTransactionId;
    private LocalDate valueDate;
    private Money transactionAmount;
    private String concept;

    public Transaction(String internalTransactionId, LocalDate valueDate, Money amount, String concept) {
        this.internalTransactionId = internalTransactionId;
        this.valueDate = valueDate;
        this.transactionAmount = amount;
        this.concept = concept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id == that.id) return true;
        return internalTransactionId.equals(that.internalTransactionId);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + internalTransactionId.hashCode();
        return result;
    }
}
