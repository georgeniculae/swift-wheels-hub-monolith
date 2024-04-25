package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.CarStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingClosingDetails {

    @NotNull(message = "Booking id cannot be null")
    private Long bookingId;

    @NotNull(message = "Receptionist employee id cannot be null")
    private Long receptionistEmployeeId;

    @NotNull(message = "Car state cannot be null")
    private CarStatus carState;

}
