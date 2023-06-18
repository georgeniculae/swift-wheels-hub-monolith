package com.carrentalservice.service;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Car;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.CarMapper;
import com.carrentalservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void saveEntity(Car car) {
        carRepository.save(car);
    }

    public Car findEntityById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }

        throw new NotFoundException("Car with id " + id + " does not exist");
    }

    public CarDto updateCar(CarDto newCarDto) {
        Car newCar = carMapper.mapDtoToEntity(newCarDto);

        Car existingCar = findEntityById(newCarDto.getId());
        Branch branch = branchService.findEntityById(newCar.getBranch().getId());
        newCar.setId(existingCar.getId());
        newCar.setBranch(branch);

        Car savedCar = carRepository.save(existingCar);

        return carMapper.mapEntityToDto(savedCar);
    }

    public void deleteCarById(Long id) {
        Car existingCar = findEntityById(id);

        Branch branch = existingCar.getBranch();
        List<Car> allCars = new ArrayList<>(branch.getCars());
        allCars.remove(existingCar);
        branch.setCars(allCars);
        branchService.saveEntity(branch);

        carRepository.deleteById(id);
    }

    public Long countCars() {
        return carRepository.count();
    }

    public Car findCarByName(String searchString) {
        return carRepository.findCarByName(searchString);
    }

    public List<Car> findCarsByMake(String make) {
        return new ArrayList<>(carRepository.findCarsByMake(make));
    }

}
