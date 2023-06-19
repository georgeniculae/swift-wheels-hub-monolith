package com.carrentalservice.service;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.mapper.RevenueMapper;
import com.carrentalservice.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevenueService {

    private final RevenueRepository revenueRepository;
    private final RevenueMapper revenueMapper;

    public List<RevenueDto> findAllRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(revenueMapper::mapEntityToDto)
                .toList();
    }

    public Revenue findRevenueByDetails(Double search) {
        return revenueRepository.findRevenueByDetails(search);
    }

}
