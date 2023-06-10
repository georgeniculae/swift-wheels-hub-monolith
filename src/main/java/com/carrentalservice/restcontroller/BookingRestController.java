package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.service.BookingService;
import com.carrentalservice.transformer.BookingTransformer;
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
    private final BookingTransformer bookingTransformer;

    @Autowired
    public BookingRestController(BookingService bookingService, BookingTransformer bookingTransformer) {
        this.bookingService = bookingService;
        this.bookingTransformer = bookingTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable("id") Long id) {
        Booking booking = bookingService.findBookingById(id);
        BookingDto bookingDto = bookingTransformer.transformFromEntityToDto(booking);

        return ResponseEntity.ok(bookingDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingDto> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingTransformer.transformFromDtoToEntity(bookingDto);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDto saveBookingDto = bookingTransformer.transformFromEntityToDto(saveBooking);

        return ResponseEntity.ok(saveBookingDto);
    }

    @PutMapping
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingTransformer.transformFromDtoToEntity(bookingDto);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDto saveBookingDto = bookingTransformer.transformFromEntityToDto(saveBooking);

        return ResponseEntity.ok(saveBookingDto);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> listAllBooking() {
        List<Booking> allBooking = bookingService.findAllBookings();
        List<BookingDto> allBookingDto = new ArrayList<>();

        for (Booking booking : allBooking) {
            allBookingDto.add(bookingTransformer.transformFromEntityToDto(booking));
        }

        return ResponseEntity.ok(allBookingDto);
    }

}
