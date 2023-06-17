package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/car")
@CrossOrigin(origins = "*")
public class CarRestController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        CarDto savedCarDto = carService.saveCar(carDto);

        return ResponseEntity.ok(savedCarDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable("id") Long id) {
        CarDto carDto = carService.findCarById(id);

        return ResponseEntity.ok(carDto);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) {
        CarDto updatedCarDto = carService.updateCar(carDto);

        return ResponseEntity.ok(updatedCarDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> listAllCars() {
        List<CarDto> carDtoList = carService.findAllCars();

        return ResponseEntity.ok(carDtoList);
    }

}
