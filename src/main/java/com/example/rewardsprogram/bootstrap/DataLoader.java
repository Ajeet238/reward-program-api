package com.example.rewardsprogram.bootstrap;

import com.example.rewardsprogram.model.Customer;
import com.example.rewardsprogram.model.Transaction;
import com.example.rewardsprogram.repository.CustomerRepository;
import com.example.rewardsprogram.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Loads sample customer and transaction data for the rewards API.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public DataLoader(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setName("John Doe");
        customerRepository.save(c1);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setName("Jane Smith");
        customerRepository.save(c2);

        saveTransaction(1L, 120, LocalDate.of(2023, 1, 15), c1);
        saveTransaction(2L, 80, LocalDate.of(2023, 1, 20), c1);
        saveTransaction(3L, 150, LocalDate.of(2023, 2, 10), c1);
        saveTransaction(4L, 60, LocalDate.of(2023, 2, 25), c1);
        saveTransaction(5L, 200, LocalDate.of(2023, 3, 5), c1);
        saveTransaction(6L, 90, LocalDate.of(2023, 1, 10), c2);
        saveTransaction(7L, 110, LocalDate.of(2023, 2, 15), c2);
        saveTransaction(8L, 70, LocalDate.of(2023, 3, 20), c2);
    }

    private void saveTransaction(Long id, double amount, LocalDate date, Customer customer) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setCustomer(customer);
        transactionRepository.save(transaction);
    }
}
