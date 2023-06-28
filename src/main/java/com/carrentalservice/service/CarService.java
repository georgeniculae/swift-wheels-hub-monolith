package com.carrentalservice.service;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Car;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.CarMapper;
import com.carrentalservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
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

    public CarDto updateCar(CarDto updatedCarDto) {
        Car existingCar = findEntityById(updatedCarDto.getId());

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

    public Car findCarByName(String searchString) {
        return carRepository.findCarByName(searchString);
    }

    public List<Car> findCarsByMake(String make) {
        return carRepository.findCarsByMake(make);
    }

}
