package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BookingDto;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable("id") Long id) {
        BookingDto bookingDto = bookingService.findBookingById(id);

        return ResponseEntity.ok(bookingDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingDto> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        BookingDto saveBookingDto = bookingService.saveBooking(bookingDto);

        return ResponseEntity.ok(saveBookingDto);
    }

    @PutMapping
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto) {
        BookingDto updatedBookingDto = bookingService.updateBooking(bookingDto);

        return ResponseEntity.ok(updatedBookingDto);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> listAllBooking() {
        List<BookingDto> bookingDtoList = bookingService.findAllBookings();

        return ResponseEntity.ok(bookingDtoList);
    }

}
