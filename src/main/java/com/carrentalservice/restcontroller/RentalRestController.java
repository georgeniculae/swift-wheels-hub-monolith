package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalDto;
import com.carrentalservice.entity.Rental;
import com.carrentalservice.service.RentalService;
import com.carrentalservice.transformer.RentalTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/rental")
public class RentalRestController {
    private final RentalService rentalService;
    private final RentalTransformer rentalTransformer;

    @Autowired
    public RentalRestController(RentalService rentalService, RentalTransformer rentalTransformer) {
        this.rentalService = rentalService;
        this.rentalTransformer = rentalTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalDto> findRentalById(@PathVariable("id") Long id) {
        Rental rental = rentalService.findRentalById(id);
        RentalDto rentalDTO = rentalTransformer.transformFromEntityToDTO(rental);
        return ResponseEntity.ok(rentalDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalDto> deleteRentalById(@PathVariable("id") Long id) {
        rentalService.deleteRentalById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDTO) {
        Rental rental = rentalTransformer.transformFromDTOToEntity(rentalDTO);
        Rental savedRental = rentalService.saveRental(rental);
        RentalDto savedRentalDTO = rentalTransformer.transformFromEntityToDTO(savedRental);
        return ResponseEntity.ok(savedRentalDTO);
    }

    @PutMapping
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDTO) {
        Rental rental = rentalTransformer.transformFromDTOToEntity(rentalDTO);
        Rental savedRental = rentalService.saveRental(rental);
        RentalDto savedRentalDTO = rentalTransformer.transformFromEntityToDTO(savedRental);
        return ResponseEntity.ok(savedRentalDTO);
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> listAllRentals() {
        List<Rental> allRentals = rentalService.findAllRentals();
        List<RentalDto> allRentalDTO = new ArrayList<>();

        for (Rental rental : allRentals) {
            allRentalDTO.add(rentalTransformer.transformFromEntityToDTO(rental));
        }
        return ResponseEntity.ok(allRentalDTO);
    }
}
