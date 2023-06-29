package com.carrentalservice.mapper;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RentalOfficeMapperTest {

    private final RentalOfficeMapper rentalOfficeMapper = new RentalOfficeMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);

        RentalOfficeDto rentalOfficeDto = rentalOfficeMapper.mapEntityToDto(rentalOffice);

        assertNotNull(rentalOfficeDto);
        AssertionUtils.assertRentalOffice(rentalOffice, rentalOfficeDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        RentalOfficeDto rentalOfficeDto = TestUtils.getResourceAsJson("/data/RentalOfficeDto.json", RentalOfficeDto.class);

        RentalOffice rentalOffice = rentalOfficeMapper.mapDtoToEntity(rentalOfficeDto);

        assertNotNull(rentalOffice);
        AssertionUtils.assertRentalOffice(rentalOffice, rentalOfficeDto);
    }

}
