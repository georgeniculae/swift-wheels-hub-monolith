package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BookingMapperTest {

    private final BookingMapper bookingMapper = new BookingMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);

        BookingResponse bookingRequest = bookingMapper.mapEntityToDto(booking);

        AssertionUtils.assertBookingResponse(booking, bookingRequest);
    }

    @Test
    void mapDtoToEntityTest_success() {
        Booking expectedBooking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);
        BookingRequest bookingRequest = TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingRequest.class);

        Booking actualBooking = bookingMapper.mapDtoToEntity(bookingRequest);

        assertThat(expectedBooking).usingRecursiveAssertion().isEqualTo(actualBooking);
    }

}
