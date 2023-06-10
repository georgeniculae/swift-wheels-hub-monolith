package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import com.carrentalservice.transformer.CarTransformer;
import com.carrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/car")
@CrossOrigin(origins = "*")
public class CarRestController {

    private final CarService carService;
    private final CarTransformer carTransformer;

    @Autowired
    public CarRestController(CarService carService, CarTransformer carTransformer) {
        this.carService = carService;
        this.carTransformer = carTransformer;
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto){
        Car car = carTransformer.transformFromDtoToEntity(carDto);
        Car savedCar = carService.saveCar(car);
        CarDto savedCarDto = carTransformer.transformFromEntityToDto(savedCar);

        return ResponseEntity.ok(savedCarDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable("id") Long id){
        Car car = carService.findCarById(id);
        CarDto carDto = carTransformer.transformFromEntityToDto(car);

        return ResponseEntity.ok(carDto);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto){
        Car car = carTransformer.transformFromDtoToEntity(carDto);
        Car carSaved = carService.saveCar(car);
        CarDto savedCarDto = carTransformer.transformFromEntityToDto(carSaved);

        return ResponseEntity.ok(savedCarDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> listAllCars(){
        List<Car> allCars = carService.findAllCars();
        List<CarDto> allCarsDto = new ArrayList<>();

        for(Car car: allCars){
            allCarsDto.add(carTransformer.transformFromEntityToDto(car));
        }

        return ResponseEntity.ok(allCarsDto);
    }

}
