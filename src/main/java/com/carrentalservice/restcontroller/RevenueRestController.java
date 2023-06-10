package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.service.RevenueService;
import com.carrentalservice.transformer.RevenueTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/revenue")
public class RevenueRestController {

    private final RevenueService revenueService;
    private final RevenueTransformer revenueTransformer;

    @Autowired
    public RevenueRestController(RevenueService revenueService, RevenueTransformer revenueTransformer) {
        this.revenueService = revenueService;
        this.revenueTransformer = revenueTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RevenueDto> findRevenueById(@PathVariable("id") Long id) {
        Revenue revenue = revenueService.findRevenueById(id);
        RevenueDto revenueDTO = revenueTransformer.transformFromEntityToDto(revenue);

        return ResponseEntity.ok(revenueDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RevenueDto> deleteRevenueById(@PathVariable("id") Long id) {
        revenueService.deleteRevenueById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RevenueDto> createRevenue(@RequestBody RevenueDto revenueDto) {
        Revenue revenue = revenueTransformer.transformFromDtoToEntity(revenueDto);
        Revenue savedRevenue = revenueService.saveRevenue(revenue);
        RevenueDto savedRevenueDto = revenueTransformer.transformFromEntityToDto(savedRevenue);

        return ResponseEntity.ok(savedRevenueDto);
    }

    @PutMapping
    public ResponseEntity<RevenueDto> updateRevenue(@RequestBody RevenueDto revenueDto) {
        Revenue revenue = revenueTransformer.transformFromDtoToEntity(revenueDto);
        Revenue savedRevenue = revenueService.saveRevenue(revenue);
        RevenueDto savedRevenueDto = revenueTransformer.transformFromEntityToDto(savedRevenue);

        return ResponseEntity.ok(savedRevenueDto);
    }

    @GetMapping
    public ResponseEntity<List<RevenueDto>> listAllRevenues() {
        List<Revenue> allRevenues = revenueService.findAllRevenues();
        List<RevenueDto> allRevenuesDto = new ArrayList<>();

        for (Revenue revenue : allRevenues) {
            allRevenuesDto.add(revenueTransformer.transformFromEntityToDto(revenue));
        }

        return ResponseEntity.ok(allRevenuesDto);
    }

}
