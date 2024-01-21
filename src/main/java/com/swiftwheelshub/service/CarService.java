package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CarDto;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.exception.NotFoundException;
import com.swiftwheelshub.mapper.CarMapper;
import com.swiftwheelshub.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BranchService branchService;
    private final CarMapper carMapper;

    public CarDto saveCar(CarDto carDto) {
        Car car = carMapper.mapDtoToEntity(carDto);

        car.setBranch(branchService.findEntityById(carDto.getBranch().getId()));
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
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }

        throw new NotFoundException("Car with id " + id + " does not exist");
    }

    public CarDto updateCar(Long id, CarDto updatedCarDto) {
        Long actualId = getId(id, updatedCarDto.getId());

        Car existingCar = findEntityById(actualId);

        Branch branch = branchService.findEntityById(updatedCarDto.getBranch().getId());

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
        Optional<Car> optionalCar = carRepository.findByFilter(searchString);

        if (optionalCar.isPresent()) {
            return carMapper.mapEntityToDto(optionalCar.get());
        }

        throw new NotFoundException("Car with filter: " + searchString + " does not exist");
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
