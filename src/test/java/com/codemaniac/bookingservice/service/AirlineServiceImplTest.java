package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.exception.AirlineNotFoundException;
import com.codemaniac.bookingservice.model.Airline;
import com.codemaniac.bookingservice.model.Audit;
import com.codemaniac.bookingservice.repository.AirlineRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codemaniac.bookingservice.service.AirlineBuilderUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AirlineServiceImplTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Before
    public void setUp() {

    }

    @Test
    public void createAirline() {
        Airline airline = getAirline();
        when(airlineRepository.save(any(Airline.class))).thenReturn(airline);
        airlineService.createAirline(airline);

        verify(airlineRepository).save(airline);
        assertEquals(Audit.PROGRAM, airline.getAudit().getCreateModule());
        assertEquals("", airline.getAudit().getUpdatedBy());
    }

    @Test
    public void testGetAirlineById() {
        Airline airline = getAirline();

        when(airlineRepository.findByIdWithStatus((TEST_AIRLINE_ID), (Audit.RECORD_STATUS_ACTIVE)))
                .thenReturn(Optional.of(airline));

        Airline result = airlineService.getAirlineById(TEST_AIRLINE_ID);

        assertEquals(airline, result);
    }

    @Test(expected = AirlineNotFoundException.class)
    public void testGetAirlineByIdNotFound() {
        Long airlineId = 1L;

        when(airlineRepository.findByIdWithStatus((airlineId), (Audit.RECORD_STATUS_ACTIVE)))
                .thenReturn(Optional.empty());

        airlineService.getAirlineById(airlineId);
    }

    @Test
    public void testGetAirlines() {
        List<Airline> airlines = new ArrayList<>();
        airlines.add(getAirline());
        airlines.add(getAirline(2L,"AA","TEST","wwe.test.com"));

        when(airlineRepository.findAllWithStatus((Audit.RECORD_STATUS_ACTIVE))).thenReturn(airlines);
        List<Airline> result = airlineService.getAirlines();

        assertEquals(airlines, result);
    }

    @Test
    public void testUpdateAirline() {
        Long airlineId = 1L;
        Airline airline = getAirline();
        when(airlineRepository.findByIdWithStatus((airlineId), (Audit.RECORD_STATUS_ACTIVE)))
                .thenReturn(Optional.of(airline));

        airlineService.updateAirline(airlineId, airline);

        verify(airlineRepository).save(airline);
        assertEquals(Audit.PROGRAM, airline.getAudit().getUpdateModule());
        assertEquals("", airline.getAudit().getUpdatedBy());
    }

    @Test(expected = AirlineNotFoundException.class)
    public void testUpdateAirlineNotFound() {
        Long airlineId = 1L;
        Airline airline = getAirline();

        when(airlineRepository.findByIdWithStatus((airlineId), (Audit.RECORD_STATUS_ACTIVE)))
                .thenReturn(Optional.empty());

        airlineService.updateAirline(airlineId, airline);
    }

    @Test
    public void testDeleteAirline() {
        Long airlineId = 1L;
        Airline airline = getAirline();

        when(airlineRepository.findById((airlineId))).thenReturn(Optional.of(airline));

        airlineService.deleteAirline(airlineId);

        assertEquals(Audit.RECORD_STATUS_DELETED, airline.getAudit().getRecordStatus());
    }

    @Test(expected = AirlineNotFoundException.class)
    public void testDeleteAirlineNotFound() {
        Long airlineId = 1L;

        when(airlineRepository.findById((airlineId))).thenReturn(Optional.empty());

        airlineService.deleteAirline(airlineId);
    }
    private Airline getAirline() {
        return AirlineBuilderUtil.builder()
                .withId(TEST_AIRLINE_ID)
                .withAirlineCode(TEST_AIRLINE_CODE)
                .withName(TEST_AIRLINE_NAME)
                .withWebsite(TEST_AIRLINE_WEBSITE)
                .withFoundedYear(LocalDate.now())
                .build();
    }

    private Airline getAirline(Long id, String code,String name, String website) {
        return AirlineBuilderUtil.builder()
                .withId(id)
                .withAirlineCode(code)
                .withName(name)
                .withWebsite(website)
                .withFoundedYear(LocalDate.now())
                .build();
    }
}