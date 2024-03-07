package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/bookings")
public class BookingRestController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponse>> findAllBooking() {
        List<BookingResponse> bookingResponses = bookingService.findAllBookings();

        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingResponse> findBookingById(@PathVariable("id") Long id) {
        BookingResponse bookingResponse = bookingService.findBookingById(id);

        return ResponseEntity.ok(bookingResponse);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countBooking() {
        Long numberOfBooking = bookingService.countBookings();

        return ResponseEntity.ok(numberOfBooking);
    }

    @GetMapping(path = "/count-by-current-user")
    public ResponseEntity<Long> countByLoggedInUser() {
        Long numberOfBooking = bookingService.countByLoggedInCustomer();

        return ResponseEntity.ok(numberOfBooking);
    }

    @GetMapping(path = "/current-date")
    public ResponseEntity<LocalDate> getCurrentDate() {
        LocalDate currentDate = bookingService.getCurrentDate();

        return ResponseEntity.ok(currentDate);
    }

    @PostMapping
    public ResponseEntity<BookingResponse> saveBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse saveBookingResponse = bookingService.saveBooking(bookingRequest);

        return ResponseEntity.ok(saveBookingResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable("id") Long id, @RequestBody BookingRequest bookingRequest) {
        BookingResponse updatedBookingResponse = bookingService.updateBooking(id, bookingRequest);

        return ResponseEntity.ok(updatedBookingResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);

        return ResponseEntity.noContent().build();
    }

}
