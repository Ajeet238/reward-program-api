package com.example.rewardsprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create customers
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setName("John Doe");
        customerRepository.save(c1);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setName("Jane Smith");
        customerRepository.save(c2);

        // Transactions for 3 months, say Jan, Feb, Mar 2023
        Transaction t1 = new Transaction();
        t1.setId(1L);
        t1.setAmount(120);
        t1.setDate(LocalDate.of(2023, 1, 15));
        t1.setCustomer(c1);
        transactionRepository.save(t1);

        Transaction t2 = new Transaction();
        t2.setId(2L);
        t2.setAmount(80);
        t2.setDate(LocalDate.of(2023, 1, 20));
        t2.setCustomer(c1);
        transactionRepository.save(t2);

        Transaction t3 = new Transaction();
        t3.setId(3L);
        t3.setAmount(150);
        t3.setDate(LocalDate.of(2023, 2, 10));
        t3.setCustomer(c1);
        transactionRepository.save(t3);

        Transaction t4 = new Transaction();
        t4.setId(4L);
        t4.setAmount(60);
        t4.setDate(LocalDate.of(2023, 2, 25));
        t4.setCustomer(c1);
        transactionRepository.save(t4);

        Transaction t5 = new Transaction();
        t5.setId(5L);
        t5.setAmount(200);
        t5.setDate(LocalDate.of(2023, 3, 5));
        t5.setCustomer(c1);
        transactionRepository.save(t5);

        Transaction t6 = new Transaction();
        t6.setId(6L);
        t6.setAmount(90);
        t6.setDate(LocalDate.of(2023, 1, 10));
        t6.setCustomer(c2);
        transactionRepository.save(t6);

        Transaction t7 = new Transaction();
        t7.setId(7L);
        t7.setAmount(110);
        t7.setDate(LocalDate.of(2023, 2, 15));
        t7.setCustomer(c2);
        transactionRepository.save(t7);

        Transaction t8 = new Transaction();
        t8.setId(8L);
        t8.setAmount(70);
        t8.setDate(LocalDate.of(2023, 3, 20));
        t8.setCustomer(c2);
        transactionRepository.save(t8);
    }
}