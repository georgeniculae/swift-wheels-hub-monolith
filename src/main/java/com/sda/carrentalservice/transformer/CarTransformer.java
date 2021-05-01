package com.sda.carrentalservice.transformer;

import com.sda.carrentalservice.dto.CarDTO;
import com.sda.carrentalservice.entity.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CarTransformer {

    public Car transformFromDTOToEntity(CarDTO carDTO){
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);
        return car;
    }

    public CarDTO transformFromEntityToDTO(Car car){
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }
}
