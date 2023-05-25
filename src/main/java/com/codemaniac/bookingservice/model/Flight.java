package com.codemaniac.bookingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flights")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double totalMiles;
    private int capacity;
    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Booking> flightBookings;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @Embedded
    private Audit audit;
}
