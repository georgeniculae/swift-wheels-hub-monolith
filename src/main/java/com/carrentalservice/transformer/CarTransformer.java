package com.carrentalservice.transformer;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CarTransformer {

    public Car transformFromDTOToEntity(CarDto carDTO){
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);
        return car;
    }

    public CarDto transformFromEntityToDTO(Car car){
        CarDto carDTO = new CarDto();
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }
}