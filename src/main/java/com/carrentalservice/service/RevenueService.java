package com.carrentalservice.service;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.RevenueMapper;
import com.carrentalservice.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevenueService {

    private final RevenueRepository revenueRepository;
    private final RevenueMapper revenueMapper;

    public RevenueDto saveRevenue(RevenueDto revenueDto) {
        Revenue revenue = revenueMapper.mapDtoToEntity(revenueDto);
        Revenue savedRevenue = revenueRepository.save(revenue);

        return revenueMapper.mapEntityToDto(savedRevenue);
    }

    public List<RevenueDto> findAllRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(revenueMapper::mapEntityToDto)
                .toList();
    }

    public void deleteRevenueById(Long id) {
        revenueRepository.deleteById(id);
    }

    public RevenueDto updateRevenue(RevenueDto newRevenueDto) {
        Revenue newRevenue = revenueMapper.mapDtoToEntity(newRevenueDto);

        Revenue existingRevenue = findEntityById(newRevenueDto.getId());
        newRevenue.setId(existingRevenue.getId());

        return saveRevenue(newRevenueDto);
    }

    public RevenueDto findRevenueById(Long id) {
        Revenue revenue = findEntityById(id);

        return revenueMapper.mapEntityToDto(revenue);
    }

    private Revenue findEntityById(Long id) {
        Optional<Revenue> optionalRevenue = revenueRepository.findById(id);
        if (optionalRevenue.isPresent()) {
            return optionalRevenue.get();
        }

        throw new NotFoundException("Revenue with id " + id + " does not exist");
    }

    public Long countRevenues() {
        return revenueRepository.count();
    }

    public Revenue findRevenueByDetails(Double search) {
        return revenueRepository.findRevenueByDetails(search);
    }

}
