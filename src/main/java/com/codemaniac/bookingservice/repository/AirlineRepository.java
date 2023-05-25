package com.codemaniac.bookingservice.repository;

import com.codemaniac.bookingservice.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Long> {
    @Query("SELECT a FROM Airline a WHERE a.audit.recordStatus = :status AND a.airlineId = :id")
    Optional<Airline> findByIdWithStatus(Long id, String status);

    @Query("SELECT a FROM Airline a WHERE a.audit.recordStatus = :status")
    List<Airline> findAllWithStatus(String status);
}
