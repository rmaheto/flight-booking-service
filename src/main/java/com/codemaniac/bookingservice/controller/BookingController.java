package com.codemaniac.bookingservice.controller;

import com.codemaniac.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookings")
@AllArgsConstructor
public class BookingController {
    private BookingService bookingsService;

    @PostMapping("/{customerId}/{flightId}")
    public ResponseEntity<Void> bookFlight(@RequestHeader HttpHeaders headers,
                                           @PathVariable Long customerId,
                                           @PathVariable Long flightId){
        bookingsService.bookFlight(customerId,flightId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
