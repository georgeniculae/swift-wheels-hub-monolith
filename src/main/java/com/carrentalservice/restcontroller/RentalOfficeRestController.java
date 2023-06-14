package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.mapper.RentalOfficeMapper;
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
    private final RentalOfficeMapper rentalOfficeMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> findRentalOfficeById(@PathVariable("id") Long id) {
        RentalOffice rentalOffice = rentalOfficeService.findRentalOfficeById(id);
        RentalOfficeDto rentalOfficeDto = rentalOfficeMapper.mapEntityToDto(rentalOffice);

        return ResponseEntity.ok(rentalOfficeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalOfficeDto> createRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = rentalOfficeMapper.mapDtoToEntity(rentalOfficeDTO);
        RentalOffice savedRentalOffice = rentalOfficeService.updateRentalOffice(rentalOffice);
        RentalOfficeDto savedRentalOfficeDto = rentalOfficeMapper.mapEntityToDto(savedRentalOffice);

        return ResponseEntity.ok(savedRentalOfficeDto);
    }

    @PutMapping
    public ResponseEntity<RentalOfficeDto> updateRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = rentalOfficeMapper.mapDtoToEntity(rentalOfficeDTO);
        RentalOffice updateRentalOffice = rentalOfficeService.updateRentalOffice(rentalOffice);
        RentalOfficeDto updatedRentalOfficeDto = rentalOfficeMapper.mapEntityToDto(updateRentalOffice);

        return ResponseEntity.ok(updatedRentalOfficeDto);
    }

    @GetMapping
    public ResponseEntity<List<RentalOfficeDto>> listAllRentalOffices() {
        List<RentalOfficeDto> rentalOfficeDtoList = rentalOfficeService.findAllRentalOffices()
                .stream()
                .map(rentalOfficeMapper::mapEntityToDto)
                .toList();

        return ResponseEntity.ok(rentalOfficeDtoList);
    }

}
