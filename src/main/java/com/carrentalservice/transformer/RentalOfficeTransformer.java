package com.carrentalservice.transformer;

import com.carrentalservice.dto.RentalOfficeDTO;
import com.carrentalservice.entity.RentalOffice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RentalOfficeTransformer {

    public RentalOfficeDTO transformFromEntityToDTO(RentalOffice rentalOffice) {
        RentalOfficeDTO rentalOfficeDTO = new RentalOfficeDTO();
        BeanUtils.copyProperties(rentalOffice, rentalOfficeDTO);
        return rentalOfficeDTO;
    }

    public RentalOffice transformFromDTOToEntity(RentalOfficeDTO rentalOfficeDTO) {
        RentalOffice rentalOffice = new RentalOffice();
        BeanUtils.copyProperties(rentalOfficeDTO, rentalOffice);
        return rentalOffice;
    }
}
