package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.model.Audit;
import com.codemaniac.bookingservice.model.Booking;
import com.codemaniac.bookingservice.model.Flight;
import com.codemaniac.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.Customer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerServiceImpl customerService;
    private final FlightService flightService;


    @Override
    public void bookFlight(Long customerId, Long flightId) {
        Customer customer = customerService.getCustomerInfo(customerId).block();
        if (customer != null){
            Flight flight = flightService.getFlightById(flightId);
            Booking booking = new Booking();
            booking.setCustomerId(customer.getId());
            booking.setFlight(flight);
            booking.setAudit(new Audit("",Audit.PROGRAM));
            bookingRepository.save(booking);
        }

    }
}
