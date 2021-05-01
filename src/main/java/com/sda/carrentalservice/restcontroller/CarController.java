package com.sda.carrentalservice.restcontroller;

import com.sda.carrentalservice.dto.CarDTO;
import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.service.CarService;
import com.sda.carrentalservice.transformer.CarTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/car")
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService carService;
    private final CarTransformer carTransformer;

    @Autowired
    public CarController(CarService carService, CarTransformer carTransformer) {
        this.carService = carService;
        this.carTransformer = carTransformer;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO){
        Car car = carTransformer.transformFromDTOToEntity(carDTO);
        Car savedCar = carService.saveCar(car);
        CarDTO savedCarDTO = carTransformer.transformFromEntityToDTO(savedCar);
        return ResponseEntity.ok(savedCarDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable("id") Long id){
        Car car = carService.findCarById(id);
        CarDTO carDTO = carTransformer.transformFromEntityToDTO(car);
        return ResponseEntity.ok(carDTO);
    }

    @PutMapping
    public ResponseEntity<CarDTO> updateCar(@RequestBody CarDTO carDTO){
        Car car = carTransformer.transformFromDTOToEntity(carDTO);
        Car carSaved = carService.saveCar(car);
        CarDTO savedCarDTO = carTransformer.transformFromEntityToDTO(carSaved);
        return ResponseEntity.ok(savedCarDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> listAllCars(){
        List<Car> allCars = carService.findAllCars();
        List<CarDTO> allCarsDTO = new ArrayList<>();

        for(Car car: allCars){
            allCarsDTO.add(carTransformer.transformFromEntityToDTO(car));
        }
        return ResponseEntity.ok(allCarsDTO);
    }
}
