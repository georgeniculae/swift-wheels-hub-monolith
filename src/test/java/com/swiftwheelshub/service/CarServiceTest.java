package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CarRequest;
import com.swiftwheelshub.dto.CarResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.CarMapper;
import com.swiftwheelshub.mapper.CarMapperImpl;
import com.swiftwheelshub.repository.CarRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    void findCarByIdTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));

        assertDoesNotThrow(() -> carService.findCarById(1L));
        CarResponse actualCarResponse = carService.findCarById(1L);

        assertNotNull(actualCarResponse);
        verify(carMapper, times(2)).mapEntityToDto(any(Car.class));
    }

    @Test
    void findCarByIdTest_errorOnFindingById() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException = assertThrows(SwiftWheelsHubNotFoundException.class, () -> carService.findCarById(1L));

        assertNotNull(swiftWheelsHubNotFoundException);
    }

    @Test
    void updateCarTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);
        CarRequest carRequest = TestUtils.getResourceAsJson("/data/CarRequest.json", CarRequest.class);
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(branchService.findEntityById(anyLong())).thenReturn(branch);
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarResponse updatedCarResponse = carService.updateCar(1L, carRequest);

        assertNotNull(updatedCarResponse);
    }

    @Test
    void saveCarTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);
        CarRequest carRequest = TestUtils.getResourceAsJson("/data/CarRequest.json", CarRequest.class);

        when(branchService.findEntityById(anyLong())).thenReturn(branch);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarResponse savedCarResponse = carService.saveCar(carRequest);
        AssertionUtils.assertCarResponse(car, savedCarResponse);
    }

    @Test
    void findAllCarsTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        when(carRepository.findAll()).thenReturn(List.of(car));

        List<CarResponse> carResponses = carService.findAllCars();
        AssertionUtils.assertCarResponse(car, carResponses.getFirst());
    }

    @Test
    void findCarByFilterTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        when(carRepository.findByFilter(anyString())).thenReturn(Optional.of(car));

        CarResponse carResponse = carService.findCarByFilter("Test");

        AssertionUtils.assertCarResponse(car, carResponse);
    }

    @Test
    void findCarByFilterTest_errorOnFindingByFilter() {
        when(carRepository.findByFilter(anyString())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> carService.findCarByFilter("Test"));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Car with filter: Test does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
