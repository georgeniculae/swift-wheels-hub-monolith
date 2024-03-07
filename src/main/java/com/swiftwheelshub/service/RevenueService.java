package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RevenueRequest;
import com.swiftwheelshub.dto.RevenueResponse;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.RevenueMapper;
import com.swiftwheelshub.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevenueService {

    private final RevenueRepository revenueRepository;
    private final RevenueMapper revenueMapper;

    public Revenue saveEntity(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    public BigDecimal getTotalAmount() {
        return revenueRepository.getTotalAmount();
    }

    public List<RevenueResponse> findAllRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(revenueMapper::mapEntityToDto)
                .toList();
    }

    public RevenueResponse findRevenueByDate(LocalDate dateOfRevenue) {
        return revenueRepository.findByDateOfRevenue(dateOfRevenue)
                .map(revenueMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Revenue from date: " + dateOfRevenue + " does not exist"));
    }

}
