package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.CarRequest;
import com.swiftwheelshub.dto.CarResponse;
import com.swiftwheelshub.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/api/cars")
public class CarRestController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        CarResponse savedCarResponse = carService.saveCar(carRequest);

        return ResponseEntity.ok(savedCarResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarResponse> findCarById(@PathVariable("id") Long id) {
        CarResponse carResponse = carService.findCarById(id);

        return ResponseEntity.ok(carResponse);
    }

    @GetMapping(path = "/make/{make}")
    public ResponseEntity<List<CarResponse>> findCarsByMake(@PathVariable("make") String make) {
        List<CarResponse> carResponses = carService.findCarsByMake(make);

        return ResponseEntity.ok(carResponses);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countCars() {
        Long numberOfCars = carService.countCars();

        return ResponseEntity.ok(numberOfCars);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable("id") Long id, @RequestBody CarRequest carRequest) {
        CarResponse updatedCarResponse = carService.updateCar(id, carRequest);

        return ResponseEntity.ok(updatedCarResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> listAllCars() {
        List<CarResponse> carResponses = carService.findAllCars();

        return ResponseEntity.ok(carResponses);
    }

}
