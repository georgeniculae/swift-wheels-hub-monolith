package com.sda.carrentalservice.restcontroller;

import com.sda.carrentalservice.dto.BookingDTO;
import com.sda.carrentalservice.entity.Booking;
import com.sda.carrentalservice.service.BookingService;
import com.sda.carrentalservice.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/booking")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;
    private final BookingTransformer bookingTransformer;

    @Autowired
    public BookingController(BookingService bookingService, BookingTransformer bookingTransformer) {
        this.bookingService = bookingService;
        this.bookingTransformer = bookingTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingDTO> findBookingById(@PathVariable("id") Long id) {
        Booking booking = bookingService.findBookingById(id);
        BookingDTO bookingDTO = bookingTransformer.transformFromEntityToDTO(booking);
        return ResponseEntity.ok(bookingDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingDTO> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingTransformer.transformFromDTOToEntity(bookingDTO);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDTO saveBookingDTO = bookingTransformer.transformFromEntityToDTO(saveBooking);
        return ResponseEntity.ok(saveBookingDTO);
    }

    @PutMapping
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingTransformer.transformFromDTOToEntity(bookingDTO);
        Booking saveBooking = bookingService.saveBooking(booking);
        BookingDTO saveBookingDTO = bookingTransformer.transformFromEntityToDTO(saveBooking);
        return ResponseEntity.ok(saveBookingDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> listAllBooking() {
        List<Booking> allBooking = bookingService.findAllBookings();
        List<BookingDTO> allBookingDTO = new ArrayList<>();

        for (Booking booking : allBooking) {
            allBookingDTO.add(bookingTransformer.transformFromEntityToDTO(booking));
        }
        return ResponseEntity.ok(allBookingDTO);
    }
}
