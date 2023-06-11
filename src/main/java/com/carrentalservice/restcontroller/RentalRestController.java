package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalDto;
import com.carrentalservice.entity.Rental;
import com.carrentalservice.service.RentalService;
import com.carrentalservice.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/rental")
@CrossOrigin(origins = "*")
public class RentalRestController {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @Autowired
    public RentalRestController(RentalService rentalService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalDto> findRentalById(@PathVariable("id") Long id) {
        Rental rental = rentalService.findRentalById(id);
        RentalDto rentalDTO = rentalMapper.mapFromEntityToDto(rental);

        return ResponseEntity.ok(rentalDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalDto> deleteRentalById(@PathVariable("id") Long id) {
        rentalService.deleteRentalById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapFromDtoToEntity(rentalDto);
        Rental savedRental = rentalService.saveRental(rental);
        RentalDto savedRentalDto = rentalMapper.mapFromEntityToDto(savedRental);

        return ResponseEntity.ok(savedRentalDto);
    }

    @PutMapping
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapFromDtoToEntity(rentalDto);
        Rental savedRental = rentalService.updateRental(rental);
        RentalDto savedRentalDto = rentalMapper.mapFromEntityToDto(savedRental);

        return ResponseEntity.ok(savedRentalDto);
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> listAllRentals() {
        List<Rental> allRentals = rentalService.findAllRentals();
        List<RentalDto> allRentalDto = new ArrayList<>();

        for (Rental rental : allRentals) {
            allRentalDto.add(rentalMapper.mapFromEntityToDto(rental));
        }

        return ResponseEntity.ok(allRentalDto);
    }

}
