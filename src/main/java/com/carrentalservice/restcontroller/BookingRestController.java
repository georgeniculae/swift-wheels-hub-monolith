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
        BookingDto bookingDTO = bookingTransformer.transformFromEntityToDTO(booking);
        return ResponseEntity.ok(bookingDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingDto> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDTO) {
        Booking booking = bookingTransformer.transformFromDTOToEntity(bookingDTO);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDto saveBookingDTO = bookingTransformer.transformFromEntityToDTO(saveBooking);
        return ResponseEntity.ok(saveBookingDTO);
    }

    @PutMapping
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDTO) {
        Booking booking = bookingTransformer.transformFromDTOToEntity(bookingDTO);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDto saveBookingDTO = bookingTransformer.transformFromEntityToDTO(saveBooking);
        return ResponseEntity.ok(saveBookingDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> listAllBooking() {
        List<Booking> allBooking = bookingService.findAllBookings();
        List<BookingDto> allBookingDTO = new ArrayList<>();

        for (Booking booking : allBooking) {
            allBookingDTO.add(bookingTransformer.transformFromEntityToDTO(booking));
        }
        return ResponseEntity.ok(allBookingDTO);
    }
}
