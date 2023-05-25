package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.exception.AirlineNotFoundException;
import com.codemaniac.bookingservice.model.Airline;
import com.codemaniac.bookingservice.model.Audit;
import com.codemaniac.bookingservice.repository.AirlineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;

    @Override
    public void createAirline(Airline airline) {
        airline.setAudit(new Audit("", Audit.PROGRAM));
        airlineRepository.save(airline);
    }

    @Override
    public Airline getAirlineById(Long id) {
        return airlineRepository.findByIdWithStatus(id,Audit.RECORD_STATUS_ACTIVE).orElseThrow(
                ()-> new AirlineNotFoundException("Airline not found with ID: "+id));
    }

    @Override
    public List<Airline> getAirlines() {
        return airlineRepository.findAllWithStatus(Audit.RECORD_STATUS_ACTIVE);
    }

    @Override
    public void updateAirline(Long id, Airline airline) {
        Airline foundAirline= airlineRepository.findByIdWithStatus(id,Audit.RECORD_STATUS_ACTIVE).orElseThrow(
                ()-> new AirlineNotFoundException("Airline not found with ID: "+id));
        foundAirline.setAirlineCode(airline.getAirlineCode());
        foundAirline.setName(airline.getName());
        foundAirline.setWebsite(airline.getWebsite());
        foundAirline.setFoundedYear(airline.getFoundedYear());
        foundAirline.getAudit().setUpdates("", Audit.PROGRAM);
        airlineRepository.save(foundAirline);
    }

    @Override
    public void deleteAirline(Long id) {
        Airline foundAirline= airlineRepository.findById(id).orElseThrow(
                ()-> new AirlineNotFoundException("Airline not found with ID: "+id));
        foundAirline.getAudit().setRecordStatus(Audit.RECORD_STATUS_DELETED);
    }
}
