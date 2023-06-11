package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.service.RentalOfficeService;
import com.carrentalservice.mapper.RentalOfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/rental-office")
@CrossOrigin(origins = "*")
public class RentalOfficeRestController {

    private final RentalOfficeService rentalOfficeService;
    private final RentalOfficeMapper rentalOfficeMapper;

    @Autowired
    public RentalOfficeRestController(RentalOfficeService rentalOfficeService, RentalOfficeMapper rentalOfficeMapper) {
        this.rentalOfficeService = rentalOfficeService;
        this.rentalOfficeMapper = rentalOfficeMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> findRentalOfficeById(@PathVariable("id") Long id) {
        RentalOffice rentalOffice = rentalOfficeService.findRentalOfficeById(id);
        RentalOfficeDto rentalOfficeDto = rentalOfficeMapper.mapFromEntityToDto(rentalOffice);

        return ResponseEntity.ok(rentalOfficeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalOfficeDto> createRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = rentalOfficeMapper.mapFromDtoToEntity(rentalOfficeDTO);
        RentalOffice savedRentalOffice = rentalOfficeService.updateRentalOffice(rentalOffice);
        RentalOfficeDto savedRentalOfficeDto = rentalOfficeMapper.mapFromEntityToDto(savedRentalOffice);

        return ResponseEntity.ok(savedRentalOfficeDto);
    }

    @PutMapping
    public ResponseEntity<RentalOfficeDto> updateRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = rentalOfficeMapper.mapFromDtoToEntity(rentalOfficeDTO);
        RentalOffice savedRentalOffice = rentalOfficeService.saveRentalOffice(rentalOffice);
        RentalOfficeDto savedRentalOfficeDto = rentalOfficeMapper.mapFromEntityToDto(savedRentalOffice);

        return ResponseEntity.ok(savedRentalOfficeDto);
    }

    @GetMapping
    public ResponseEntity<List<RentalOfficeDto>> listAllRentalOffices() {
        List<RentalOfficeDto> rentalOfficeDtoList = rentalOfficeService.findAllRentalOffices()
                .stream()
                .map(rentalOfficeMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(rentalOfficeDtoList);
    }

}
