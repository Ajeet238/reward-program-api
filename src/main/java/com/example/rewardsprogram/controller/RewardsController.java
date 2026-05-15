package com.example.rewardsprogram.controller;

import com.example.rewardsprogram.dto.RewardsResponse;
import com.example.rewardsprogram.service.RewardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * REST controller exposing rewards calculation endpoints.
 */
@Tag(name = "Rewards API", description = "Calculates monthly and total reward points for a customer")
@RestController
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @Operation(summary = "Get reward points for a customer", description = "Returns monthly and total reward points for the requested customer between the given dates.")
    @GetMapping("/rewards/{customerId}")
    public RewardsResponse getRewards(
            @Parameter(description = "The customer identifier", example = "1") @PathVariable Long customerId,
            @Parameter(description = "Inclusive start date", example = "2023-01-01") @RequestParam String startDate,
            @Parameter(description = "Inclusive end date", example = "2023-03-31") @RequestParam String endDate) {
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
