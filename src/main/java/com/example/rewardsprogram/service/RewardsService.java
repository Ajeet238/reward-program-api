package com.example.rewardsprogram.service;

import com.example.rewardsprogram.dto.RewardsResponse;
import com.example.rewardsprogram.exception.CustomerNotFoundException;
import com.example.rewardsprogram.model.Transaction;
import com.example.rewardsprogram.repository.CustomerRepository;
import com.example.rewardsprogram.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates reward points by processing stored transactions.
 */
@Service
public class RewardsService {

    private static final int FIRST_THRESHOLD = 50;
    private static final int SECOND_THRESHOLD = 100;

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RewardsService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Calculates reward points for a single transaction amount.
     *
     * @param amount the transaction amount
     * @return the number of points earned
     */
    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > SECOND_THRESHOLD) {
            points += 2 * (int) (amount - SECOND_THRESHOLD);
            amount = SECOND_THRESHOLD;
        }
        if (amount > FIRST_THRESHOLD) {
            points += (int) (amount - FIRST_THRESHOLD);
        }
        return points;
    }

    /**
     * Aggregates reward points for a customer between two dates.
     *
     * @param customerId the customer identifier
     * @param startDate the inclusive start date
     * @param endDate the inclusive end date
     * @return rewards summary with monthly and total points
     */
    public RewardsResponse getRewardsForCustomer(Long customerId, LocalDate startDate, LocalDate endDate) {
        if (customerId == null || !customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }

        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);

        Map<String, Integer> monthlyPoints = new LinkedHashMap<>();
        int totalPoints = 0;

        for (Transaction transaction : transactions) {
            YearMonth month = YearMonth.from(transaction.getDate());
            int points = calculatePoints(transaction.getAmount());
            String monthKey = month.toString();
            monthlyPoints.put(monthKey, monthlyPoints.getOrDefault(monthKey, 0) + points);
            totalPoints += points;
        }

        return new RewardsResponse(customerId, monthlyPoints, totalPoints);
    }
}
