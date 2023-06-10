package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.service.RentalOfficeService;
import com.carrentalservice.transformer.RentalOfficeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/rentaloffice")
public class RentalOfficeRestController {

    private final RentalOfficeService rentalOfficeService;
    private final RentalOfficeTransformer rentalOfficeTransformer;

    @Autowired
    public RentalOfficeRestController(RentalOfficeService rentalOfficeService, RentalOfficeTransformer rentalOfficeTransformer) {
        this.rentalOfficeService = rentalOfficeService;
        this.rentalOfficeTransformer = rentalOfficeTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> findRentalOfficeById(@PathVariable("id") Long id) {
        RentalOffice rentalOffice = rentalOfficeService.findRentalOfficeById(id);
        RentalOfficeDto rentalOfficeDTO = rentalOfficeTransformer.transformFromEntityToDTO(rentalOffice);
        return ResponseEntity.ok(rentalOfficeDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalOfficeDto> deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RentalOfficeDto> createRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = rentalOfficeTransformer.transformFromDTOToEntity(rentalOfficeDTO);
        RentalOffice savedRentalOffice = rentalOfficeService.saveRentalOffice(rentalOffice);
        RentalOfficeDto savedRentalOfficeDTO = rentalOfficeTransformer.transformFromEntityToDTO(savedRentalOffice);
        return ResponseEntity.ok(savedRentalOfficeDTO);
    }

    @PutMapping
    public ResponseEntity<RentalOfficeDto> updateRentalOffice(@RequestBody RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = rentalOfficeTransformer.transformFromDTOToEntity(rentalOfficeDTO);
        RentalOffice savedRentalOffice = rentalOfficeService.saveRentalOffice(rentalOffice);
        RentalOfficeDto savedRentalOfficeDTO = rentalOfficeTransformer.transformFromEntityToDTO(savedRentalOffice);
        return ResponseEntity.ok(savedRentalOfficeDTO);
    }

    @GetMapping
    public ResponseEntity<List<RentalOfficeDto>> listAllRentalOffices() {
        List<RentalOffice> allRentalOffices = rentalOfficeService.findAllRentalOffices();
        List<RentalOfficeDto> allRentalOfficesDTO = new ArrayList<>();

        for (RentalOffice rentalOffice : allRentalOffices) {
            allRentalOfficesDTO.add(rentalOfficeTransformer.transformFromEntityToDTO(rentalOffice));
        }
        return ResponseEntity.ok(allRentalOfficesDTO);
    }
}
