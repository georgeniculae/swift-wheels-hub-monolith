package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.RevenueDto;
import com.swiftwheelshub.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/revenue")
@CrossOrigin(origins = "*")
public class RevenueRestController {

    private final RevenueService revenueService;

    @GetMapping
    public ResponseEntity<List<RevenueDto>> listAllRevenues() {
        List<RevenueDto> revenueDtoList = revenueService.findAllRevenues();

        return ResponseEntity.ok(revenueDtoList);
    }

    @GetMapping(path = "/total")
    public ResponseEntity<BigDecimal> getTotalAmount() {
        BigDecimal totalAmount = revenueService.getTotalAmount();

        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping(path = "/{date}")
    public ResponseEntity<RevenueDto> findRevenueByDate(@RequestParam("date") LocalDate dateOfRevenue) {
        RevenueDto revenue = revenueService.findRevenueByDate(dateOfRevenue);

        return ResponseEntity.ok(revenue);
    }

}
