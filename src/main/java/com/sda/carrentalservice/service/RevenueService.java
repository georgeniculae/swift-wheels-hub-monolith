package com.sda.carrentalservice.service;

import com.sda.carrentalservice.entity.Revenue;
import com.sda.carrentalservice.exception.NotFoundException;
import com.sda.carrentalservice.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevenueService {

    private final RevenueRepository revenueRepository;

    @Autowired
    public RevenueService(RevenueRepository revenueRepository) {
        this.revenueRepository = revenueRepository;
    }

    public Revenue saveRevenue(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    public List<Revenue> findAllRevenues() {
        return revenueRepository.findAll();
    }

    public void deleteRevenueById(Long id) {
        revenueRepository.deleteById(id);
    }

    public Revenue findRevenueById(Long id) {
        Optional<Revenue> optionalRevenue = revenueRepository.findById(id);
        if (optionalRevenue.isPresent()) {
            Revenue revenue = optionalRevenue.get();
            return revenue;
        } else {
            throw new NotFoundException("Revenue with id " + id + " does not exist.");
        }
    }

    public Long countRevenues() {
        return revenueRepository.count();
    }

    public Revenue findRevenueByDetails(Double search) {
        return revenueRepository.findRevenueByDetails(search);
    }
}
