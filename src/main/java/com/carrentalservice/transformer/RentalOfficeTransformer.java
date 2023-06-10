package com.carrentalservice.transformer;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RentalOfficeTransformer {

    public RentalOfficeDto transformFromEntityToDTO(RentalOffice rentalOffice) {
        RentalOfficeDto rentalOfficeDTO = new RentalOfficeDto();
        BeanUtils.copyProperties(rentalOffice, rentalOfficeDTO);
        return rentalOfficeDTO;
    }

    public RentalOffice transformFromDTOToEntity(RentalOfficeDto rentalOfficeDTO) {
        RentalOffice rentalOffice = new RentalOffice();
        BeanUtils.copyProperties(rentalOfficeDTO, rentalOffice);
        return rentalOffice;
    }
}
