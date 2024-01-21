package com.swiftwheelshub.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class SwiftWheelsHubException extends ResponseStatusException {

    public SwiftWheelsHubException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
