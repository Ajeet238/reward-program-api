package com.example.rewardsprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class RewardsService {

    @Autowired
    private TransactionRepository transactionRepository;

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += 2 * (amount - 100);
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    public Map<String, Object> getRewardsForCustomer(Long customerId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);

        Map<YearMonth, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;

        for (Transaction t : transactions) {
            YearMonth month = YearMonth.from(t.getDate());
            int points = calculatePoints(t.getAmount());
            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
            totalPoints += points;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("customerId", customerId);
        result.put("monthlyPoints", monthlyPoints);
        result.put("totalPoints", totalPoints);

        return result;
    }

}