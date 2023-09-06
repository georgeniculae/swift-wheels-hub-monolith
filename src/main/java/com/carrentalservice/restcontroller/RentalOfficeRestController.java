package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.service.RentalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
public class RentalOfficeRestController {

    private final RentalOfficeService rentalOfficeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> findRentalOfficeById(@PathVariable("id") Long id) {
        RentalOfficeDto rentalOfficeDto = rentalOfficeService.findRentalOfficeById(id);

        return ResponseEntity.ok(rentalOfficeDto);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countRentalOffices() {
        Long numberOfRentalOffice = rentalOfficeService.countRentalOffices();

        return ResponseEntity.ok(numberOfRentalOffice);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalOfficeDto> createRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDto) {
        RentalOfficeDto savedRentalOfficeDto = rentalOfficeService.saveRentalOffice(rentalOfficeDto);

        return ResponseEntity.ok(savedRentalOfficeDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> updateRentalOffice(@PathVariable("id") Long id,
                                                              @RequestBody RentalOfficeDto rentalOfficeDto) {
        RentalOfficeDto updatedRentalOfficeDto = rentalOfficeService.updateRentalOffice(id, rentalOfficeDto);

        return ResponseEntity.ok(updatedRentalOfficeDto);
    }

    @GetMapping
    public ResponseEntity<List<RentalOfficeDto>> listAllRentalOffices() {
        List<RentalOfficeDto> rentalOfficeDtoList = rentalOfficeService.findAllRentalOffices();

        return ResponseEntity.ok(rentalOfficeDtoList);
    }

}
