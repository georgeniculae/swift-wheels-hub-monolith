package com.carrentalservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
@ControllerAdvice
public class CustomExceptionHandler {

    private final GlobalErrorAttributes globalErrorAttributes;

    @Autowired
    public CustomExceptionHandler(GlobalErrorAttributes globalErrorAttributes) {
        this.globalErrorAttributes = globalErrorAttributes;
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException e, WebRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = globalErrorAttributes.getErrorAttributes(request, options);
        errorAttributes.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAttributes);
    }

}
