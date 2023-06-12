package com.carrentalservice.service;

import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Car;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final BranchService branchService;

    @Autowired
    public CarService(CarRepository carRepository, BranchService branchRepository) {
        this.carRepository = carRepository;
        this.branchService = branchRepository;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }

        throw new NotFoundException("Car with id " + id + " does not exist.");
    }

    public Car updateCar(Car newCar) {
        Car existingCar = findCarById(newCar.getId());
        newCar.setId(existingCar.getId());

        return saveCar(newCar);
    }

    public void deleteCarById(Long id) {
        Car existingCar = findCarById(id);

        Branch branch = existingCar.getBranch();
        List<Car> allCars = new ArrayList<>(branch.getCars());
        allCars.remove(existingCar);
        branch.setCars(allCars);
        branchService.saveBranch(branch);

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
