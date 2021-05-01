package com.sda.carrentalservice.restcontroller;

import com.sda.carrentalservice.dto.RevenueDTO;
import com.sda.carrentalservice.entity.Revenue;
import com.sda.carrentalservice.service.RevenueService;
import com.sda.carrentalservice.transformer.RevenueTransformer;
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
    public ResponseEntity<RevenueDTO> findRevenueById(@PathVariable("id") Long id) {
        Revenue revenue = revenueService.findRevenueById(id);
        RevenueDTO revenueDTO = revenueTransformer.transformFromEntityToDTO(revenue);
        return ResponseEntity.ok(revenueDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RevenueDTO> deleteRevenueById(@PathVariable("id") Long id) {
        revenueService.deleteRevenueById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RevenueDTO> createRevenue(@RequestBody RevenueDTO revenueDTO) {
        Revenue revenue = revenueTransformer.transformFromDTOToEntity(revenueDTO);
        Revenue savedRevenue = revenueService.saveRevenue(revenue);
        RevenueDTO savedRevenueDTO = revenueTransformer.transformFromEntityToDTO(savedRevenue);
        return ResponseEntity.ok(savedRevenueDTO);
    }

    @PutMapping
    public ResponseEntity<RevenueDTO> updateRevenue(@RequestBody RevenueDTO revenueDTO) {
        Revenue revenue = revenueTransformer.transformFromDTOToEntity(revenueDTO);
        Revenue savedRevenue = revenueService.saveRevenue(revenue);
        RevenueDTO savedRevenueDTO = revenueTransformer.transformFromEntityToDTO(savedRevenue);
        return ResponseEntity.ok(savedRevenueDTO);
    }

    @GetMapping
    public ResponseEntity<List<RevenueDTO>> listAllRevenues() {
        List<Revenue> allRevenues = revenueService.findAllRevenues();
        List<RevenueDTO> allRevenuesDTO = new ArrayList<>();

        for (Revenue revenue : allRevenues) {
            allRevenuesDTO.add(revenueTransformer.transformFromEntityToDTO(revenue));
        }
        return ResponseEntity.ok(allRevenuesDTO);
    }
}
