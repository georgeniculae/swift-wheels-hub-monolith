package com.swiftwheelshub.dto;

import com.swiftwheelshub.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookingDetails {

    private Long id;
    private BookingStatus status;

}
