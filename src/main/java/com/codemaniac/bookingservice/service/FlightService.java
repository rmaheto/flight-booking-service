package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.model.Flight;

import java.util.List;

public interface FlightService {

    public void createFlight(Long airlineId, Flight flight);
    public Flight getFlightById(Long flightId);
    public List<Flight> findAllFlight(Long airlineId);
    public void updateFlight(Long airlineId,Long flightId, Flight flight);
}
