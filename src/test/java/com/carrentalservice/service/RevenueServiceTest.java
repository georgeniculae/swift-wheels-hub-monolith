package com.carrentalservice.service;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.RevenueMapper;
import com.carrentalservice.mapper.RevenueMapperImpl;
import com.carrentalservice.repository.RevenueRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        assertDoesNotThrow(() -> revenueService.findAllRevenues());
        List<RevenueDto> revenueDtoList = revenueService.findAllRevenues();

        AssertionUtils.assertRevenue(revenue, revenueDtoList.get(0));

        verify(revenueMapper, times(2)).mapEntityToDto(any(Revenue.class));
    }

    @Test
    void findRevenueByDateTest_success() {
        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);

        when(revenueRepository.findByDateOfRevenue(any(Date.class))).thenReturn(Optional.of(revenue));

        assertDoesNotThrow(() -> revenueService.findRevenueByDate(Date.valueOf("2050-02-20")));
        RevenueDto revenueDto = revenueService.findRevenueByDate(Date.valueOf("2050-02-20"));

        AssertionUtils.assertRevenue(revenue, revenueDto);

        verify(revenueMapper, times(2)).mapEntityToDto(any(Revenue.class));
    }

    @Test
    void findRevenueByDateTest_errorOnFindingByDateOfRevenue() {
        when(revenueRepository.findByDateOfRevenue(any(Date.class))).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> revenueService.findRevenueByDate(Date.valueOf("2050-02-20")));

        assertNotNull(notFoundException);
        assertEquals("Revenue from date: 2050-02-20 does not exist", notFoundException.getMessage());
    }

}
