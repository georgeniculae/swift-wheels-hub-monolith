package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.RevenueResponse;
import com.swiftwheelshub.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/revenues")
public class RevenueRestController {

    private final RevenueService revenueService;

    @GetMapping
    public ResponseEntity<List<RevenueResponse>> listAllRevenues() {
        List<RevenueResponse> revenueResponses = revenueService.findAllRevenues();

        return ResponseEntity.ok(revenueResponses);
    }

    @GetMapping(path = "/total")
    public ResponseEntity<BigDecimal> getTotalAmount() {
        BigDecimal totalAmount = revenueService.getTotalAmount();

        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping(path = "/{date}")
    public ResponseEntity<RevenueResponse> findRevenueByDate(@PathVariable("date") LocalDate dateOfRevenue) {
        RevenueResponse revenueResponse = revenueService.findRevenueByDate(dateOfRevenue);

        return ResponseEntity.ok(revenueResponse);
    }

}
