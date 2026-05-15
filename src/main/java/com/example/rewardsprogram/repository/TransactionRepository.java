package com.example.rewardsprogram.repository;

import com.example.rewardsprogram.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository that retrieves customer transactions for reward calculations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerIdAndDateBetween(Long customerId, LocalDate start, LocalDate end);
}
