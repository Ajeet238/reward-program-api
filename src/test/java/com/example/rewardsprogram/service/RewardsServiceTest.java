package com.example.rewardsprogram.service;

import com.example.rewardsprogram.dto.RewardsResponse;
import com.example.rewardsprogram.model.Transaction;
import com.example.rewardsprogram.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RewardsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private RewardsService rewardsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rewardsService = new RewardsService(transactionRepository);
    }

    @Test
    void calculatePoints_zeroForAmountBelowThreshold() {
        assertEquals(0, rewardsService.calculatePoints(40));
    }

    @Test
    void calculatePoints_onePointPerDollarBetween50And100() {
        assertEquals(25, rewardsService.calculatePoints(75));
    }

    @Test
    void calculatePoints_twoPointsPerDollarAbove100() {
        assertEquals(90, rewardsService.calculatePoints(120));
    }

    @Test
    void getRewardsForCustomer_returnsMonthlyAndTotalPoints() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 31);

        Transaction first = new Transaction();
        first.setId(1L);
        first.setAmount(120);
        first.setDate(LocalDate.of(2023, 1, 15));

        Transaction second = new Transaction();
        second.setId(2L);
        second.setAmount(80);
        second.setDate(LocalDate.of(2023, 2, 10));

        when(transactionRepository.findByCustomerIdAndDateBetween(1L, startDate, endDate))
                .thenReturn(List.of(first, second));

        RewardsResponse response = rewardsService.getRewardsForCustomer(1L, startDate, endDate);

        assertEquals(1L, response.getCustomerId());
        assertEquals(2, response.getMonthlyPoints().size());
        assertEquals(40, response.getTotalPoints());
        assertEquals(40, response.getMonthlyPoints().get("2023-01"));
        assertEquals(30, response.getMonthlyPoints().get("2023-02"));
    }
}
