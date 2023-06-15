package com.carrentalservice.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlingTest {

    private final ExceptionHandling exceptionHandling = new ExceptionHandling();

    @Test
    void handleNotFoundExceptionTest() {
        NotFoundException notFoundException = new NotFoundException("Resource not found");

        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(servletRequest);

        ResponseEntity<Map<String, Object>> responseEntity =
                exceptionHandling.handleNotFoundException(notFoundException, webRequest);

        assertEquals(404, responseEntity.getStatusCode().value());
    }

}
