package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CarDto;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.CarMapper;
import com.swiftwheelshub.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BranchService branchService;
    private final CarMapper carMapper;

    public CarDto saveCar(CarDto carDto) {
        Car car = carMapper.mapDtoToEntity(carDto);

        car.setBranch(branchService.findEntityById(carDto.getBranchDetails().getId()));
        Car savedCar = carRepository.save(car);

        return carMapper.mapEntityToDto(savedCar);
    }

    public List<CarDto> findAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::mapEntityToDto)
                .toList();
    }

    public CarDto findCarById(Long id) {
        Car car = findEntityById(id);

        return carMapper.mapEntityToDto(car);
    }

    public Car findEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Car with id " + id + " does not exist"));
    }

    public CarDto updateCar(Long id, CarDto updatedCarDto) {
        Long actualId = getId(id, updatedCarDto.getId());

        Car existingCar = findEntityById(actualId);

        Branch branch = branchService.findEntityById(updatedCarDto.getBranchDetails().getId());

        existingCar.setMake(updatedCarDto.getMake());
        existingCar.setModel(updatedCarDto.getModel());
        existingCar.setBodyType(updatedCarDto.getBodyType());
        existingCar.setYearOfProduction(updatedCarDto.getYearOfProduction());
        existingCar.setColor(updatedCarDto.getColor());
        existingCar.setMileage(updatedCarDto.getMileage());
        existingCar.setAmount(updatedCarDto.getAmount());
        existingCar.setCarStatus(updatedCarDto.getCarStatus());
        existingCar.setBranch(branch);
        existingCar.setUrlOfImage(updatedCarDto.getUrlOfImage());

        Car savedCar = carRepository.save(existingCar);

        return carMapper.mapEntityToDto(savedCar);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public Long countCars() {
        return carRepository.count();
    }

    public CarDto findCarByFilter(String searchString) {
        return carRepository.findByFilter(searchString)
                .map(carMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Car with filter: " + searchString + " does not exist"));
    }

    public List<CarDto> findCarsByMake(String make) {
        return carRepository.findCarsByMake(make)
                .stream()
                .map(carMapper::mapEntityToDto)
                .toList();
    }

    private Long getId(Long id, Long updatedCarId) {
        Long actualId = updatedCarId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
