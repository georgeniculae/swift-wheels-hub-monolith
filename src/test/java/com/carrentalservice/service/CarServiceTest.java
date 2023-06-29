package com.carrentalservice.service;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Car;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.CarMapper;
import com.carrentalservice.mapper.CarMapperImpl;
import com.carrentalservice.repository.CarRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Test
    void findCarByIdTest_success() {
        Car car = TestData.createCar();

        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));

        assertDoesNotThrow(() -> carService.findCarById(1L));
        CarDto actualCarDto = carService.findCarById(1L);

        assertNotNull(actualCarDto);
        verify(carMapper, times(2)).mapEntityToDto(any(Car.class));
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

    @Test
    void saveCarTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);
        CarDto carDto = TestUtils.getResourceAsJson("/data/CarDto.json", CarDto.class);

        when(branchService.findEntityById(anyLong())).thenReturn(branch);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        assertDoesNotThrow(() -> carService.saveCar(carDto));
        CarDto savedCarDto = carService.saveCar(carDto);
        AssertionUtils.assertCar(car, savedCarDto);
    }

    @Test
    void findAllCarsTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        when(carRepository.findAll()).thenReturn(List.of(car));

        assertDoesNotThrow(() -> carService.findAllCars());
        List<CarDto> carDtoList = carService.findAllCars();
        AssertionUtils.assertCar(car, carDtoList.get(0));
    }

    @Test
    void findCarByFilterTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        when(carRepository.findByFilter(anyString())).thenReturn(Optional.of(car));

        assertDoesNotThrow(() -> carService.findCarByFilter("Test"));
        CarDto carDto = carService.findCarByFilter("Test");

        AssertionUtils.assertCar(car, carDto);
    }

    @Test
    void findCarByFilterTest_errorOnFindingByFilter() {
        when(carRepository.findByFilter(anyString())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> carService.findCarByFilter("Test"));

        assertNotNull(notFoundException);
        assertEquals("Car with filter: Test does not exist", notFoundException.getMessage());
    }

}
