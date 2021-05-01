package com.sda.carrentalservice.transformer;

import com.sda.carrentalservice.dto.BookingDTO;
import com.sda.carrentalservice.entity.Booking;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookingTransformer {

    public Booking transformFromDTOToEntity(BookingDTO bookingDTO){
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDTO, booking);
        return booking;
    }

    public BookingDTO transformFromEntityToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, bookingDTO);
        return bookingDTO;
    }

}
