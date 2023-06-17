package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/revenue")
@CrossOrigin(origins = "*")
public class RevenueRestController {

    private final RevenueService revenueService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RevenueDto> findRevenueById(@PathVariable("id") Long id) {
        RevenueDto revenueDto = revenueService.findRevenueById(id);

        return ResponseEntity.ok(revenueDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RevenueDto> deleteRevenueById(@PathVariable("id") Long id) {
        revenueService.deleteRevenueById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RevenueDto> createRevenue(@RequestBody RevenueDto revenueDto) {
        RevenueDto savedRevenueDto = revenueService.saveRevenue(revenueDto);

        return ResponseEntity.ok(savedRevenueDto);
    }

    @PutMapping
    public ResponseEntity<RevenueDto> updateRevenue(@RequestBody RevenueDto revenueDto) {
        RevenueDto updatedRevenueDto = revenueService.updateRevenue(revenueDto);

        return ResponseEntity.ok(updatedRevenueDto);
    }

    @GetMapping
    public ResponseEntity<List<RevenueDto>> listAllRevenues() {
        List<RevenueDto> revenueDtoList = revenueService.findAllRevenues();

        return ResponseEntity.ok(revenueDtoList);
    }

}
