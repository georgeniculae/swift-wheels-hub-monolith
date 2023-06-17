package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.service.RentalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/rental-office")
@CrossOrigin(origins = "*")
public class RentalOfficeRestController {

    private final RentalOfficeService rentalOfficeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> findRentalOfficeById(@PathVariable("id") Long id) {
        RentalOfficeDto rentalOfficeDto = rentalOfficeService.findRentalOfficeById(id);

        return ResponseEntity.ok(rentalOfficeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalOfficeDto> createRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDto) {
        RentalOfficeDto savedRentalOfficeDto = rentalOfficeService.updateRentalOffice(rentalOfficeDto);

        return ResponseEntity.ok(savedRentalOfficeDto);
    }

    @PutMapping
    public ResponseEntity<RentalOfficeDto> updateRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDto) {
        RentalOfficeDto updatedRentalOfficeDto = rentalOfficeService.updateRentalOffice(rentalOfficeDto);

        return ResponseEntity.ok(updatedRentalOfficeDto);
    }

    @GetMapping
    public ResponseEntity<List<RentalOfficeDto>> listAllRentalOffices() {
        List<RentalOfficeDto> rentalOfficeDtoList = rentalOfficeService.findAllRentalOffices();

        return ResponseEntity.ok(rentalOfficeDtoList);
    }

}
