package com.codemaniac.bookingservice.advice;

import com.codemaniac.bookingservice.exception.AirlineNotFoundException;
import com.codemaniac.bookingservice.exception.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(AirlineNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(AirlineNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<String> handleFlightNotFoundException(FlightNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
