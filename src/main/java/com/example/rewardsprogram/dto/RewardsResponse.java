package com.example.rewardsprogram.dto;

import java.util.Collections;
import java.util.Map;

/**
 * Data transfer object for rewards calculations.
 */
public final class RewardsResponse {

    private final Long customerId;
    private final Map<String, Integer> monthlyPoints;
    private final int totalPoints;

    public RewardsResponse(Long customerId, Map<String, Integer> monthlyPoints, int totalPoints) {
        this.customerId = customerId;
        this.monthlyPoints = monthlyPoints == null ? Collections.emptyMap() : Collections.unmodifiableMap(monthlyPoints);
        this.totalPoints = totalPoints;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getMonthlyPoints() {
        return monthlyPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
