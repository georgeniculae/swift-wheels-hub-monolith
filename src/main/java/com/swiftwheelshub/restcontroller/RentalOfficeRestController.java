package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.RentalOfficeRequest;
import com.swiftwheelshub.dto.RentalOfficeResponse;
import com.swiftwheelshub.service.RentalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/rental-office")
public class RentalOfficeRestController {

    private final RentalOfficeService rentalOfficeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeResponse> findRentalOfficeById(@PathVariable("id") Long id) {
        RentalOfficeResponse rentalOfficeResponse = rentalOfficeService.findRentalOfficeById(id);

        return ResponseEntity.ok(rentalOfficeResponse);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countRentalOffices() {
        Long numberOfRentalOffice = rentalOfficeService.countRentalOffices();

        return ResponseEntity.ok(numberOfRentalOffice);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalOfficeResponse> createRentalOffice(@RequestBody RentalOfficeRequest rentalOfficeRequest) {
        RentalOfficeResponse savedRentalOfficeResponse = rentalOfficeService.saveRentalOffice(rentalOfficeRequest);

        return ResponseEntity.ok(savedRentalOfficeResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeResponse> updateRentalOffice(@PathVariable("id") Long id,
                                                                   @RequestBody RentalOfficeRequest rentalOfficeRequest) {
        RentalOfficeResponse updatedRentalOfficeResponse = rentalOfficeService.updateRentalOffice(id, rentalOfficeRequest);

        return ResponseEntity.ok(updatedRentalOfficeResponse);
    }

    @GetMapping
    public ResponseEntity<List<RentalOfficeResponse>> listAllRentalOffices() {
        List<RentalOfficeResponse> rentalOfficeResponses = rentalOfficeService.findAllRentalOffices();

        return ResponseEntity.ok(rentalOfficeResponses);
    }

}
