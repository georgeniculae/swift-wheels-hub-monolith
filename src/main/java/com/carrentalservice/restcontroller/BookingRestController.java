package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.service.BookingService;
import com.carrentalservice.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/booking")
@CrossOrigin(origins = "*")
public class BookingRestController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingRestController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable("id") Long id) {
        Booking booking = bookingService.findBookingById(id);
        BookingDto bookingDto = bookingMapper.mapFromEntityToDto(booking);

        return ResponseEntity.ok(bookingDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingDto> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.mapFromDtoToEntity(bookingDto);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDto saveBookingDto = bookingMapper.mapFromEntityToDto(saveBooking);

        return ResponseEntity.ok(saveBookingDto);
    }

    @PutMapping
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.mapFromDtoToEntity(bookingDto);
        Booking saveBooking = bookingService.updateBooking(booking);
        BookingDto saveBookingDto = bookingMapper.mapFromEntityToDto(saveBooking);

        return ResponseEntity.ok(saveBookingDto);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> listAllBooking() {
        List<BookingDto> bookingDtoList = bookingService.findAllBookings()
                .stream()
                .map(bookingMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(bookingDtoList);
    }

}
