package com.carrentalservice.service;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.RevenueMapper;
import com.carrentalservice.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public Double getTotalAmount() {
        return revenueRepository.getTotalAmount();
    }

    public List<RevenueDto> findAllRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(revenueMapper::mapEntityToDto)
                .toList();
    }

    public RevenueDto findRevenueByDate(Date dateOfRevenue) {
        Optional<Revenue> optionalRevenue = revenueRepository.findByDateOfRevenue(dateOfRevenue);

        if (optionalRevenue.isPresent()) {
            return revenueMapper.mapEntityToDto(optionalRevenue.get());
        }

        throw new NotFoundException("Revenue from date: " + dateOfRevenue + " does not exist");
    }

}
