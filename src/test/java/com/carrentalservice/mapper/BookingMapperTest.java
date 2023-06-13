package com.carrentalservice.mapper;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingMapperTest {

    private final BookingMapper bookingMapper = new BookingMapperImpl();

    @Test
    void mapDtoToEntityTest_success() {
        Booking booking = TestData.createBooking();
        BookingDto bookingDto = bookingMapper.mapEntityToDto(booking);

        AssertionUtils.assertBooking(booking, bookingDto);
    }

}
