package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalDto;
import com.carrentalservice.entity.Rental;
import com.carrentalservice.mapper.RentalMapper;
import com.carrentalservice.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/rental")
@CrossOrigin(origins = "*")
public class RentalRestController {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalDto> findRentalById(@PathVariable("id") Long id) {
        Rental rental = rentalService.findRentalById(id);
        RentalDto rentalDTO = rentalMapper.mapEntityToDto(rental);

        return ResponseEntity.ok(rentalDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalDto> deleteRentalById(@PathVariable("id") Long id) {
        rentalService.deleteRentalById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapDtoToEntity(rentalDto);
        Rental savedRental = rentalService.saveRental(rental);
        RentalDto savedRentalDto = rentalMapper.mapEntityToDto(savedRental);

        return ResponseEntity.ok(savedRentalDto);
    }

    @PutMapping
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapDtoToEntity(rentalDto);
        Rental updateRental = rentalService.updateRental(rental);
        RentalDto updatedRentalDto = rentalMapper.mapEntityToDto(updateRental);

        return ResponseEntity.ok(updatedRentalDto);
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> listAllRentals() {
        List<RentalDto> rentalDtoList = rentalService.findAllRentals()
                .stream()
                .map(rentalMapper::mapEntityToDto)
                .toList();

        return ResponseEntity.ok(rentalDtoList);
    }

}
