package com.carrentalservice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CarRentalServiceException extends ResponseStatusException {

    public CarRentalServiceException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
