package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
