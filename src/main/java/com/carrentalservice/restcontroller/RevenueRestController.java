package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.service.RevenueService;
import com.carrentalservice.mapper.RevenueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/revenue")
@CrossOrigin(origins = "*")
public class RevenueRestController {

    private final RevenueService revenueService;
    private final RevenueMapper revenueMapper;

    @Autowired
    public RevenueRestController(RevenueService revenueService, RevenueMapper revenueMapper) {
        this.revenueService = revenueService;
        this.revenueMapper = revenueMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RevenueDto> findRevenueById(@PathVariable("id") Long id) {
        Revenue revenue = revenueService.findRevenueById(id);
        RevenueDto revenueDTO = revenueMapper.mapFromEntityToDto(revenue);

        return ResponseEntity.ok(revenueDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RevenueDto> deleteRevenueById(@PathVariable("id") Long id) {
        revenueService.deleteRevenueById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RevenueDto> createRevenue(@RequestBody RevenueDto revenueDto) {
        Revenue revenue = revenueMapper.mapFromDtoToEntity(revenueDto);
        Revenue savedRevenue = revenueService.saveRevenue(revenue);
        RevenueDto savedRevenueDto = revenueMapper.mapFromEntityToDto(savedRevenue);

        return ResponseEntity.ok(savedRevenueDto);
    }

    @PutMapping
    public ResponseEntity<RevenueDto> updateRevenue(@RequestBody RevenueDto revenueDto) {
        Revenue revenue = revenueMapper.mapFromDtoToEntity(revenueDto);
        Revenue savedRevenue = revenueService.updateRevenue(revenue);
        RevenueDto savedRevenueDto = revenueMapper.mapFromEntityToDto(savedRevenue);

        return ResponseEntity.ok(savedRevenueDto);
    }

    @GetMapping
    public ResponseEntity<List<RevenueDto>> listAllRevenues() {
        List<RevenueDto> revenueDtoList = revenueService.findAllRevenues()
                .stream()
                .map(revenueMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(revenueDtoList);
    }

}
