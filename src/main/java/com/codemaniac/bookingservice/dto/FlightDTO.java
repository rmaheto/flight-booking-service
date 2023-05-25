package com.codemaniac.bookingservice.dto;

import com.codemaniac.bookingservice.model.Airline;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class FlightDTO {
    private Long flightId;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;
    private Airline airline;
    private String createdBy;
    private String createModule;
    private Calendar createTimestamp;
}
