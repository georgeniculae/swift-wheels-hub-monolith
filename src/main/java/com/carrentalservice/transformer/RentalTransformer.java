package com.carrentalservice.transformer;

import com.carrentalservice.dto.RentalDTO;
import com.carrentalservice.entity.Rental;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RentalTransformer {
    public Rental transformFromDTOToEntity(RentalDTO rentalDTO){
        Rental rental = new Rental();
        BeanUtils.copyProperties(rentalDTO, rental);
        return rental;
    }

    public RentalDTO transformFromEntityToDTO(Rental rental){
        RentalDTO rentalDTO = new RentalDTO();
        BeanUtils.copyProperties(rental, rentalDTO);
        return rentalDTO;

    }
}
