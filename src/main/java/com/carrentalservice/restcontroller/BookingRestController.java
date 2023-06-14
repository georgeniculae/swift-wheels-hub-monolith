package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.mapper.BookingMapper;
import com.carrentalservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/booking")
@CrossOrigin(origins = "*")
public class BookingRestController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable("id") Long id) {
        Booking booking = bookingService.findBookingById(id);
        BookingDto bookingDto = bookingMapper.mapEntityToDto(booking);

        return ResponseEntity.ok(bookingDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingDto> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.mapDtoToEntity(bookingDto);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDto saveBookingDto = bookingMapper.mapEntityToDto(saveBooking);

        return ResponseEntity.ok(saveBookingDto);
    }

    @PutMapping
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.mapDtoToEntity(bookingDto);
        Booking updatedBooking = bookingService.updateBooking(booking);
        BookingDto updatedBookingDto = bookingMapper.mapEntityToDto(updatedBooking);

        return ResponseEntity.ok(updatedBookingDto);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> listAllBooking() {
        List<BookingDto> bookingDtoList = bookingService.findAllBookings()
                .stream()
                .map(bookingMapper::mapEntityToDto)
                .toList();

        return ResponseEntity.ok(bookingDtoList);
    }

}
