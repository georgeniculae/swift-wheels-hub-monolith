package com.carrentalservice.mapper;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Car;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BookingMapperTest {

    private final BookingMapper bookingMapper = new BookingMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Booking booking = TestData.createBooking();
        Car car = TestData.createCar();
        car.setBookings(List.of(booking));
        booking.setCar(car);

        BookingDto bookingDto = bookingMapper.mapEntityToDto(booking);

        AssertionUtils.assertBooking(booking, bookingDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        Booking expectedBooking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);
        BookingDto bookingDto = TestUtils.getResourceAsJson("/data/BookingDto.json", BookingDto.class);

        Booking actualBooking = bookingMapper.mapDtoToEntity(bookingDto);

        assertThat(expectedBooking).usingRecursiveAssertion().isEqualTo(actualBooking);
    }

}
