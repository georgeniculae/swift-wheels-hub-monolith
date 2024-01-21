package com.swiftwheelshub.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling extends DefaultErrorAttributes {

    private static final String MESSAGE = "message";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException e, WebRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        Map<String, Object> errorAttributes = getErrorAttributesMap(request, e.getMessage(), RESOURCE_NOT_FOUND, notFound);

        return ResponseEntity.status(notFound).body(errorAttributes);
    }

    @ExceptionHandler(SwiftWheelsHubException.class)
    public ResponseEntity<Map<String, Object>> handleSwiftWheelsHubException(SwiftWheelsHubException e, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Map<String, Object> errorAttributes = getErrorAttributesMap(request, e.getMessage(), e.getReason(), badRequest);

        return ResponseEntity.status(badRequest).body(errorAttributes);
    }

    private Map<String, Object> getErrorAttributesMap(WebRequest webRequest, String errorMessage, String cause, HttpStatus httpStatus) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());

        return addErrorAttributes(errorAttributes, errorMessage, cause, httpStatus);
    }

    private Map<String, Object> addErrorAttributes(Map<String, Object> errorAttributes, String errorMessage, String cause, HttpStatus httpStatus) {
        errorAttributes.put(MESSAGE, errorMessage);
        errorAttributes.put(STATUS, httpStatus.value());
        errorAttributes.put(ERROR, cause);

        return errorAttributes;
    }

}
