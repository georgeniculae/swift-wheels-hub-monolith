package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RevenueRequest;
import com.swiftwheelshub.dto.RevenueResponse;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.RevenueMapper;
import com.swiftwheelshub.mapper.RevenueMapperImpl;
import com.swiftwheelshub.repository.RevenueRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RevenueServiceTest {

    @InjectMocks
    private RevenueService revenueService;

    @Mock
    private RevenueRepository revenueRepository;

    @Spy
    private RevenueMapper revenueMapper = new RevenueMapperImpl();

    @Test
    void findAllRevenuesTest_success() {
        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);

        when(revenueRepository.findAll()).thenReturn(List.of(revenue));

        List<RevenueResponse> revenueResponses = revenueService.findAllRevenues();

        AssertionUtils.assertRevenueResponse(revenue, revenueResponses.getFirst());

        verify(revenueMapper).mapEntityToDto(any(Revenue.class));
    }

    @Test
    void findRevenueByDateTest_success() {
        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);

        when(revenueRepository.findByDateOfRevenue(any(LocalDate.class))).thenReturn(Optional.of(revenue));

        assertDoesNotThrow(() -> revenueService.findRevenueByDate(LocalDate.parse("2050-02-20")));
        RevenueResponse revenueResponse = revenueService.findRevenueByDate(LocalDate.parse("2050-02-20"));

        AssertionUtils.assertRevenueResponse(revenue, revenueResponse);

        verify(revenueMapper, times(2)).mapEntityToDto(any(Revenue.class));
    }

    @Test
    void findRevenueByDateTest_errorOnFindingByDateOfRevenue() {
        when(revenueRepository.findByDateOfRevenue(any(LocalDate.class))).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> revenueService.findRevenueByDate(LocalDate.parse("2050-02-20")));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Revenue from date: 2050-02-20 does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
