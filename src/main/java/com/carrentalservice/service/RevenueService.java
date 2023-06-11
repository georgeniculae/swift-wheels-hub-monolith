package com.carrentalservice.service;

import com.carrentalservice.entity.Revenue;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.RevenueRepository;
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

    public Revenue updateRevenue(Revenue newRevenue) {
        Revenue existingRevenue = findRevenueById(newRevenue.getId());
        newRevenue.setId(existingRevenue.getId());

        return saveRevenue(newRevenue);
    }

    public Revenue findRevenueById(Long id) {
        Optional<Revenue> optionalRevenue = revenueRepository.findById(id);
        if (optionalRevenue.isPresent()) {
            return optionalRevenue.get();
        }

        throw new NotFoundException("Revenue with id " + id + " does not exist.");
    }

    public Long countRevenues() {
        return revenueRepository.count();
    }

    public Revenue findRevenueByDetails(Double search) {
        return revenueRepository.findRevenueByDetails(search);
    }

}
