package com.facespedes.finanzas.context.apibank.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t FROM Transaction t WHERE t.valueDate >= :date")
    List<Transaction> findAllBefore(LocalDate date);
}
