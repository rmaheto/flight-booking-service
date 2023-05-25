package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.model.Airline;

import java.util.List;

public interface AirlineService {
    public void createAirline(Airline airline);
    public Airline getAirlineById(Long id);
    public List<Airline> getAirlines();
    public void updateAirline(Long id, Airline airline);
    public void deleteAirline(Long id);
}
