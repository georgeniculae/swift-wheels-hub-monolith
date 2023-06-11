package com.carrentalservice.mapper;

import com.carrentalservice.dto.RentalDto;
import com.carrentalservice.entity.Rental;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public Rental mapFromDtoToEntity(RentalDto rentalDTO){
        Rental rental = new Rental();
        BeanUtils.copyProperties(rentalDTO, rental);

        return rental;
    }

    public RentalDto mapFromEntityToDto(Rental rental){
        RentalDto rentalDTO = new RentalDto();
        BeanUtils.copyProperties(rental, rentalDTO);

        return rentalDTO;
    }

}
