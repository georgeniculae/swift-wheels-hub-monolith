package com.carrentalservice.transformer;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car mapFromDtoToEntity(CarDto carDTO){
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);

        return car;
    }

    public CarDto mapFromEntityToDto(Car car){
        CarDto carDTO = new CarDto();
        BeanUtils.copyProperties(car, carDTO);

        return carDTO;
    }

}
