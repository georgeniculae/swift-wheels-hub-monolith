package com.sda.carrentalservice.restcontroller;

import com.sda.carrentalservice.dto.RentalDTO;
import com.sda.carrentalservice.entity.Rental;
import com.sda.carrentalservice.service.RentalService;
import com.sda.carrentalservice.transformer.RentalTransformer;
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
    public ResponseEntity<RentalDTO> findRentalById(@PathVariable("id") Long id) {
        Rental rental = rentalService.findRentalById(id);
        RentalDTO rentalDTO = rentalTransformer.transformFromEntityToDTO(rental);
        return ResponseEntity.ok(rentalDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalDTO> deleteRentalById(@PathVariable("id") Long id) {
        rentalService.deleteRentalById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentalDTO) {
        Rental rental = rentalTransformer.transformFromDTOToEntity(rentalDTO);
        Rental savedRental = rentalService.saveRental(rental);
        RentalDTO savedRentalDTO = rentalTransformer.transformFromEntityToDTO(savedRental);
        return ResponseEntity.ok(savedRentalDTO);
    }

    @PutMapping
    public ResponseEntity<RentalDTO> updateRental(@RequestBody RentalDTO rentalDTO) {
        Rental rental = rentalTransformer.transformFromDTOToEntity(rentalDTO);
        Rental savedRental = rentalService.saveRental(rental);
        RentalDTO savedRentalDTO = rentalTransformer.transformFromEntityToDTO(savedRental);
        return ResponseEntity.ok(savedRentalDTO);
    }

    @GetMapping
    public ResponseEntity<List<RentalDTO>> listAllRentals() {
        List<Rental> allRentals = rentalService.findAllRentals();
        List<RentalDTO> allRentalDTO = new ArrayList<>();

        for (Rental rental : allRentals) {
            allRentalDTO.add(rentalTransformer.transformFromEntityToDTO(rental));
        }
        return ResponseEntity.ok(allRentalDTO);
    }
}
