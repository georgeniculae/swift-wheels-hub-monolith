package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import com.carrentalservice.mapper.CarMapper;
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
    private final CarMapper carMapper;

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        Car car = carMapper.mapDtoToEntity(carDto);
        Car savedCar = carService.saveCar(car);
        CarDto savedCarDto = carMapper.mapEntityToDto(savedCar);

        return ResponseEntity.ok(savedCarDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable("id") Long id) {
        Car car = carService.findCarById(id);
        CarDto carDto = carMapper.mapEntityToDto(car);

        return ResponseEntity.ok(carDto);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) {
        Car car = carMapper.mapDtoToEntity(carDto);
        Car updatedCar = carService.updateCar(car);
        CarDto updatedCarDto = carMapper.mapEntityToDto(updatedCar);

        return ResponseEntity.ok(updatedCarDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> listAllCars() {
        List<CarDto> carDtoList = carService.findAllCars()
                .stream()
                .map(carMapper::mapEntityToDto)
                .toList();

        return ResponseEntity.ok(carDtoList);
    }

}
