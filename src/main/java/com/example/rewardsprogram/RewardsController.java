package com.example.rewardsprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.Map;

@RestController
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/rewards/{customerId}")
    public Map<String, Object> getRewards(@PathVariable Long customerId,
                                          @RequestParam String startDate,
                                          @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return rewardsService.getRewardsForCustomer(customerId, start, end);
    }

}