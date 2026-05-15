package com.example.rewardsprogram.controller;

import com.example.rewardsprogram.dto.RewardsResponse;
import com.example.rewardsprogram.service.RewardsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * REST controller exposing rewards calculation endpoints.
 */
@RestController
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping("/rewards/{customerId}")
    public RewardsResponse getRewards(@PathVariable Long customerId,
                                      @RequestParam String startDate,
                                      @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        validateDateRange(start, end);
        return rewardsService.getRewardsForCustomer(customerId, start, end);
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("endDate must be the same or after startDate");
        }
    }
}
