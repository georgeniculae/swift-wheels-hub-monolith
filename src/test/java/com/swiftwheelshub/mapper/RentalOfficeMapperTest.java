package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.RentalOfficeRequest;
import com.swiftwheelshub.dto.RentalOfficeResponse;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
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

        RentalOfficeResponse rentalOfficeRequest = rentalOfficeMapper.mapEntityToDto(rentalOffice);

        assertNotNull(rentalOfficeRequest);
        AssertionUtils.assertRentalOfficeResponse(rentalOffice, rentalOfficeRequest);
    }

    @Test
    void mapDtoToEntityTest_success() {
        RentalOfficeRequest rentalOfficeRequest = TestUtils.getResourceAsJson("/data/RentalOfficeRequest.json", RentalOfficeRequest.class);

        RentalOffice rentalOffice = rentalOfficeMapper.mapDtoToEntity(rentalOfficeRequest);

        assertNotNull(rentalOffice);
        AssertionUtils.assertRentalOfficeRequest(rentalOffice, rentalOfficeRequest);
    }

}
