package com.codemaniac.bookingservice.controller;

import com.codemaniac.bookingservice.model.Flight;
import com.codemaniac.bookingservice.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
@AllArgsConstructor
public class FlightController {

    private FlightService flightService;

    @PostMapping("/{airlineId}")
    public ResponseEntity<Void> createFlight(@RequestHeader HttpHeaders headers,
                                             @PathVariable Long airlineId,
                                             @RequestBody Flight flight){
        flightService.createFlight(airlineId,flight);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{flightId}")
    public ResponseEntity<Flight> getFlight(@RequestHeader HttpHeaders headers,
                                            @PathVariable Long flightId){
        return new ResponseEntity<>(flightService.getFlightById(flightId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights(@RequestHeader HttpHeaders headers,
                                                     @RequestParam(required = false) Long airlineId) {
        return new ResponseEntity<>(flightService.findAllFlight(airlineId),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateFlight(@RequestHeader HttpHeaders headers,
                                             @PathVariable Long airlineId,
                                             @PathVariable Long flightId,
                                             @RequestBody Flight flight){
        flightService.updateFlight(airlineId,flightId,flight);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
