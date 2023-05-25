package com.codemaniac.bookingservice.controller;

import com.codemaniac.bookingservice.model.Airline;
import com.codemaniac.bookingservice.service.AirlineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/airlines")
@AllArgsConstructor
public class AirlineController {

    private AirlineService airlineService;

    @PostMapping
    private ResponseEntity<Void> createAirline(@RequestHeader HttpHeaders headers,
                                               @RequestBody Airline airline){
        airlineService.createAirline(airline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<Airline>> getAirlines(@RequestHeader HttpHeaders headers){
          return new ResponseEntity<>(airlineService.getAirlines(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Airline> getAirlineById(@PathVariable Long id){
        Airline airline = airlineService.getAirlineById(id);
        if (airline== null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Airline>(airline,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateAirline(@PathVariable Long id,
                                               @RequestBody Airline airline,
                                               @RequestHeader HttpHeaders headers){
        airlineService.updateAirline(id, airline);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
