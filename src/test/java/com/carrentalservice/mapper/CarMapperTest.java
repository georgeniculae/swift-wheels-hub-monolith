package com.carrentalservice.mapper;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {

    private final CarMapper carMapper = new CarMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        CarDto carDto = carMapper.mapEntityToDto(car);

        assertNotNull(carDto);
        AssertionUtils.assertCar(car, carDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        CarDto carDto = TestUtils.getResourceAsJson("/data/CarDto.json", CarDto.class);

        Car car = carMapper.mapDtoToEntity(carDto);

        assertNotNull(carDto);
        AssertionUtils.assertCar(car, carDto);
    }

}
