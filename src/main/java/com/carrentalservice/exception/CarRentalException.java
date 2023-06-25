package com.carrentalservice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CarRentalException extends ResponseStatusException {

    public CarRentalException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
