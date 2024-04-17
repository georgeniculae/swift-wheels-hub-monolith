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
public class UpdateCarRequest {
    @NotNull(message = "Car id cannot be null")
    Long carId;

    @NotNull(message = "Car state cannot be null")
    CarStatus carState;

    @Override
    public String toString() {
        return "UpdateCarRequest{" + "\n" +
                "carId=" + carId + "\n" +
                "carState=" + carState + "\n" +
                "}";
    }

}
