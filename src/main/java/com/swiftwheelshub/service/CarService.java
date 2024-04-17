package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CarRequest;
import com.swiftwheelshub.dto.CarResponse;
import com.swiftwheelshub.dto.UpdateCarRequest;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.CarStatus;
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

    public CarResponse saveCar(CarRequest carRequest) {
        Car car = carMapper.mapDtoToEntity(carRequest);

        car.setOriginalBranch(branchService.findEntityById(carRequest.getBranchDetails().getId()));
        car.setActualBranch(branchService.findEntityById(carRequest.getBranchDetails().getId()));
        Car savedCar = carRepository.save(car);

        return carMapper.mapEntityToDto(savedCar);
    }

    public List<CarResponse> findAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::mapEntityToDto)
                .toList();
    }

    public CarResponse findCarById(Long id) {
        Car car = findEntityById(id);

        return carMapper.mapEntityToDto(car);
    }

    public Car findEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Car with id " + id + " does not exist"));
    }

    public CarResponse updateCar(Long id, CarRequest updatedCarRequest) {
        Car existingCar = findEntityById(id);

        Branch branch = branchService.findEntityById(updatedCarRequest.getBranchDetails().getId());

        existingCar.setMake(updatedCarRequest.getMake());
        existingCar.setModel(updatedCarRequest.getModel());
        existingCar.setBodyType(updatedCarRequest.getBodyType());
        existingCar.setYearOfProduction(updatedCarRequest.getYearOfProduction());
        existingCar.setColor(updatedCarRequest.getColor());
        existingCar.setMileage(updatedCarRequest.getMileage());
        existingCar.setAmount(updatedCarRequest.getAmount());
        existingCar.setCarStatus(updatedCarRequest.getCarStatus());
        existingCar.setActualBranch(branch);

        Car savedCar = carRepository.save(existingCar);

        return carMapper.mapEntityToDto(savedCar);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public Long countCars() {
        return carRepository.count();
    }

    public CarResponse findCarByFilter(String searchString) {
        return carRepository.findByFilter(searchString)
                .map(carMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Car with filter: " + searchString + " does not exist"));
    }

    public List<CarResponse> findCarsByMake(String make) {
        return carRepository.findCarsByMake(make)
                .stream()
                .map(carMapper::mapEntityToDto)
                .toList();
    }

    private List<Long> getIds(List<UpdateCarRequest> carsForUpdate) {
        return carsForUpdate.stream()
                .map(UpdateCarRequest::getCarId)
                .toList();
    }

    public List<CarResponse> updateCarsStatus(List<UpdateCarRequest> updateCarRequests) {
        List<Car> updatableCars = carRepository.findAllById(getIds(updateCarRequests));

        updatableCars.forEach(car -> car.setCarStatus(getUpdatedCarStatus(updateCarRequests, car)));

        return carRepository.saveAllAndFlush(updatableCars)
                .stream()
                .map(carMapper::mapEntityToDto)
                .toList();
    }

    private CarStatus getUpdatedCarStatus(List<UpdateCarRequest> updateCarRequests, Car car) {
        UpdateCarRequest matchingUpdateCarRequest = updateCarRequests.stream()
                .filter(updateCarRequest -> car.getId().equals(updateCarRequest.getCarId()))
                .findAny()
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Car details not found"));

        return matchingUpdateCarRequest.getCarState();
    }

    private Long getId(Long id, Long updatedCarId) {
        Long actualId = updatedCarId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
