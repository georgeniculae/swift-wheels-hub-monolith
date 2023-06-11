package com.carrentalservice.mapper;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking mapDtoToEntity(BookingDto bookingDTO){
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDTO, booking);

        return booking;
    }

    public BookingDto mapEntityToDto(Booking booking) {
        BookingDto bookingDTO = new BookingDto();
        BeanUtils.copyProperties(booking, bookingDTO);

        return bookingDTO;
    }

}
