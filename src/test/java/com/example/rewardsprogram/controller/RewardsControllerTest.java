package com.example.rewardsprogram.controller;

import com.example.rewardsprogram.dto.RewardsResponse;
import com.example.rewardsprogram.service.RewardsService;
import com.example.rewardsprogram.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RewardsController.class)
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardsService rewardsService;

    @Test
    void getRewards_returnsExpectedResponse() throws Exception {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 31);
        RewardsResponse response = new RewardsResponse(1L, Map.of("2023-01", 40, "2023-02", 30), 70);

        when(rewardsService.getRewardsForCustomer(eq(1L), eq(startDate), eq(endDate)))
                .thenReturn(response);

        mockMvc.perform(get("/rewards/1")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-03-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.totalPoints").value(70))
                .andExpect(jsonPath("$.monthlyPoints['2023-01']").value(40));
    }

    @Test
    void getRewards_returnsNotFoundWhenCustomerMissing() throws Exception {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 31);

        when(rewardsService.getRewardsForCustomer(eq(99L), eq(startDate), eq(endDate)))
                .thenThrow(new CustomerNotFoundException(99L));

        mockMvc.perform(get("/rewards/99")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-03-31"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Customer with id 99 not found"));
    }
}
