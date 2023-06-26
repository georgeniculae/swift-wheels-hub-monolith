package com.carrentalservice.service;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BranchMapper;
import com.carrentalservice.mapper.BranchMapperImpl;
import com.carrentalservice.mapper.CarMapper;
import com.carrentalservice.mapper.CarMapperImpl;
import com.carrentalservice.repository.CarRepository;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private BranchService branchService;

    @Spy
    private CarMapper carMapper = new CarMapperImpl();

    @Spy
    private BranchMapper branchMapper = new BranchMapperImpl();

    @Test
    void findCarByIdTest_success() {
        Car car = TestData.createCar();

        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));

        assertDoesNotThrow(() -> carService.findCarById(1L));
        CarDto actualCarDto = carService.findCarById(1L);

        assertNotNull(actualCarDto);
    }

    @Test
    void findCarByIdTest_errorOnFindingById() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> carService.findCarById(1L));

        assertNotNull(notFoundException);
    }

    @Test
    void updateCarTest_success() {
        Car car = TestData.createCar();
        CarDto carDto = TestData.createCarDto();

        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(car);

        assertDoesNotThrow(() -> carService.updateCar(carDto));
        CarDto updatedCarDto = carService.updateCar(carDto);

        assertNotNull(updatedCarDto);
    }

}
