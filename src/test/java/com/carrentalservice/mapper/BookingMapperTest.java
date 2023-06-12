package com.carrentalservice.mapper;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BookingMapperTest {

    @InjectMocks
    private BookingMapper bookingMapper;

    @Test
    void mapDtoToEntityTest_success() {
        Booking booking = TestData.createBooking();
        BookingDto bookingDto = bookingMapper.mapEntityToDto(booking);

        assertThat(booking).usingRecursiveAssertion().isEqualTo(bookingDto);
    }

}
