package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RevenueDto;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.exception.NotFoundException;
import com.swiftwheelshub.mapper.RevenueMapper;
import com.swiftwheelshub.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<RevenueDto> findAllRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(revenueMapper::mapEntityToDto)
                .toList();
    }

    public RevenueDto findRevenueByDate(LocalDate dateOfRevenue) {
        Optional<Revenue> optionalRevenue = revenueRepository.findByDateOfRevenue(dateOfRevenue);

        if (optionalRevenue.isPresent()) {
            return revenueMapper.mapEntityToDto(optionalRevenue.get());
        }

        throw new NotFoundException("Revenue from date: " + dateOfRevenue + " does not exist");
    }

}
