package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import com.carrentalservice.mapper.CarMapper;
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
    private final CarMapper carMapper;

    @Autowired
    public CarRestController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto){
        Car car = carMapper.mapFromDtoToEntity(carDto);
        Car savedCar = carService.saveCar(car);
        CarDto savedCarDto = carMapper.mapFromEntityToDto(savedCar);

        return ResponseEntity.ok(savedCarDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable("id") Long id){
        Car car = carService.findCarById(id);
        CarDto carDto = carMapper.mapFromEntityToDto(car);

        return ResponseEntity.ok(carDto);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto){
        Car car = carMapper.mapFromDtoToEntity(carDto);
        Car carSaved = carService.updateCar(car);
        CarDto savedCarDto = carMapper.mapFromEntityToDto(carSaved);

        return ResponseEntity.ok(savedCarDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> listAllCars(){
        List<CarDto> carDtoList = carService.findAllCars()
                .stream()
                .map(carMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(carDtoList);
    }

}
