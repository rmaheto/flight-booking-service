package com.codemaniac.bookingservice.service;

import com.codemaniac.bookingservice.model.Airline;
import com.codemaniac.bookingservice.model.Audit;

import java.time.LocalDate;

public class AirlineBuilderUtil {
    private Airline airline;
    public static final Long TEST_AIRLINE_ID =1L;
    public static final String TEST_AIRLINE_NAME = "TEST AIRLINE";
    public static final String TEST_AIRLINE_CODE = "TA";
    public static final String TEST_AIRLINE_WEBSITE = "www.testairline.com";

    private AirlineBuilderUtil() {
        airline = new Airline();
        airline.setAudit(new Audit());
    }

    public static AirlineBuilderUtil builder() {
        return new AirlineBuilderUtil();
    }

    public AirlineBuilderUtil withId(Long id) {
        airline.setAirlineId(id);
        return this;
    }

    public AirlineBuilderUtil withAirlineCode(String airlineCode) {
        airline.setAirlineCode(airlineCode);
        return this;
    }

    public AirlineBuilderUtil withName(String name) {
        airline.setName(name);
        return this;
    }

    public AirlineBuilderUtil withWebsite(String website) {
        airline.setWebsite(website);
        return this;
    }

    public AirlineBuilderUtil withFoundedYear(LocalDate foundedYear) {
        airline.setFoundedYear(foundedYear);
        return this;
    }

    public Airline build() {
        return airline;
    }
}
