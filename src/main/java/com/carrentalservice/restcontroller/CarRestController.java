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
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDTO){
        Car car = carTransformer.transformFromDTOToEntity(carDTO);
        Car savedCar = carService.saveCar(car);
        CarDto savedCarDTO = carTransformer.transformFromEntityToDTO(savedCar);
        return ResponseEntity.ok(savedCarDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable("id") Long id){
        Car car = carService.findCarById(id);
        CarDto carDTO = carTransformer.transformFromEntityToDTO(car);
        return ResponseEntity.ok(carDTO);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDTO){
        Car car = carTransformer.transformFromDTOToEntity(carDTO);
        Car carSaved = carService.saveCar(car);
        CarDto savedCarDTO = carTransformer.transformFromEntityToDTO(carSaved);
        return ResponseEntity.ok(savedCarDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> listAllCars(){
        List<Car> allCars = carService.findAllCars();
        List<CarDto> allCarsDTO = new ArrayList<>();

        for(Car car: allCars){
            allCarsDTO.add(carTransformer.transformFromEntityToDTO(car));
        }
        return ResponseEntity.ok(allCarsDTO);
    }
}
