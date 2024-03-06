package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RevenueDto;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.exception.NotFoundException;
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

        assertDoesNotThrow(() -> revenueService.findAllRevenues());
        List<RevenueDto> revenueDtoList = revenueService.findAllRevenues();

        AssertionUtils.assertRevenue(revenue, revenueDtoList.getFirst());

        verify(revenueMapper, times(2)).mapEntityToDto(any(Revenue.class));
    }

    @Test
    void findRevenueByDateTest_success() {
        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);

        when(revenueRepository.findByDateOfRevenue(any(LocalDate.class))).thenReturn(Optional.of(revenue));

        assertDoesNotThrow(() -> revenueService.findRevenueByDate(LocalDate.parse("2050-02-20")));
        RevenueDto revenueDto = revenueService.findRevenueByDate(LocalDate.parse("2050-02-20"));

        AssertionUtils.assertRevenue(revenue, revenueDto);

        verify(revenueMapper, times(2)).mapEntityToDto(any(Revenue.class));
    }

    @Test
    void findRevenueByDateTest_errorOnFindingByDateOfRevenue() {
        when(revenueRepository.findByDateOfRevenue(any(LocalDate.class))).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> revenueService.findRevenueByDate(LocalDate.parse("2050-02-20")));

        assertNotNull(notFoundException);
        assertEquals("Revenue from date: 2050-02-20 does not exist", notFoundException.getMessage());
    }

}
