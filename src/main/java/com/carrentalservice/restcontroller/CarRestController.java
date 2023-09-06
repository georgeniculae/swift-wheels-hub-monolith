package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.service.CarService;
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

    @GetMapping(path = "/make/{make}")
    public ResponseEntity<List<CarDto>> findCarsByMake(@PathVariable("make") String make) {
        List<CarDto> carDtoList = carService.findCarsByMake(make);

        return ResponseEntity.ok(carDtoList);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countCars() {
        Long numberOfCars = carService.countCars();

        return ResponseEntity.ok(numberOfCars);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") Long id, @RequestBody CarDto carDto) {
        CarDto updatedCarDto = carService.updateCar(id, carDto);

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
