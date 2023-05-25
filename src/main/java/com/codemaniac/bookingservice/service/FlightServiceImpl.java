package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.exception.AirlineNotFoundException;
import com.codemaniac.bookingservice.exception.FlightNotFoundException;
import com.codemaniac.bookingservice.model.Airline;
import com.codemaniac.bookingservice.model.Audit;
import com.codemaniac.bookingservice.model.Flight;
import com.codemaniac.bookingservice.repository.AirlineRepository;
import com.codemaniac.bookingservice.repository.FlightRepository;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private AirlineRepository airlineRepository;

    @Override
    public void createFlight(Long airlineId, Flight flight) {
        Airline foundAirline = airlineRepository.findByIdWithStatus(airlineId, Audit.RECORD_STATUS_ACTIVE).orElseThrow(
                () -> new AirlineNotFoundException("Airline not found with ID: " + airlineId));
        if (foundAirline != null) {
            flight.setAirline(foundAirline);
            flight.setAudit(new Audit("", Audit.PROGRAM));
            flightRepository.save(flight);
        }
    }

    @Override
    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with flightId: " + flightId));
    }

    @Override
    public List<Flight> findAllFlight(Long airlineId) {
        Specification<Flight> specification = buildFindAllFlightSpecification(airlineId);
        return flightRepository.findAll(specification);
    }

    @Override
    public void updateFlight(Long airlineId, Long flightId, Flight flight) {
        Flight flightToUpdate = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with flightId: " + flightId));
        Airline airline = airlineRepository.findById(airlineId).
                orElseThrow(() -> new AirlineNotFoundException("Airline not found with airlineId: " + airlineId));

        flightToUpdate.setAirline(airline);
        flightToUpdate.setOrigin(flight.getOrigin());
        flightToUpdate.setDestination(flight.getDestination());
        flightToUpdate.setDepartureTime(flight.getDepartureTime());
        flightToUpdate.setTotalMiles(flight.getTotalMiles());
        flightToUpdate.setCapacity(flight.getCapacity());
        flightToUpdate.getAudit().setUpdates("", Audit.PROGRAM);
        flightRepository.save(flightToUpdate);
    }

    private Specification<Flight> buildFindAllFlightSpecification(Long airlineId) {
        return new Specification<Flight>() {
            @Override
            public Predicate toPredicate(Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<Flight, Airline> airlineJoin = root.join("airline");

                predicates.add(criteriaBuilder.equal(airlineJoin.get("audit").get("recordStatus"),
                        Audit.RECORD_STATUS_ACTIVE));
                predicates.add(criteriaBuilder.equal(root.get("audit").get("recordStatus"),
                        Audit.RECORD_STATUS_ACTIVE));

                if (airlineId != null) {
                    predicates.add(criteriaBuilder.equal(airlineJoin.get("airlineId"), airlineId));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
