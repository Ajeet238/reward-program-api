package com.example.rewardsprogram.repository;

import com.example.rewardsprogram.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for customer records.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
