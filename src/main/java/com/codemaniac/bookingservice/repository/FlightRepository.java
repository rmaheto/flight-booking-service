package com.codemaniac.bookingservice.repository;

import com.codemaniac.bookingservice.model.Flight;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long>{

    List<Flight> findAll(Specification<Flight> specification);

//    public Optional<Flight> findByAirlineIdAndFlightId(Long airlineId, Long flightId) {
//        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
//        CriteriaQuery<Flight> query = criteriaBuilder.createQuery(Flight.class);
//        Root<Flight> flightRoot = query.from(Flight.class);
//
//        // Join with the airline entity
//        Join<Flight, Airline> airlineJoin = flightRoot.join("airline");
//
//        // Apply conditions for airlineId, flightId, and recordStatus
//        query.where(
//                criteriaBuilder.equal(airlineJoin.get("airlineId"), airlineId),
//                criteriaBuilder.equal(flightRoot.get("flightId"), flightId),
//                criteriaBuilder.equal(flightRoot.get("audit").get("recordStatus"), Audit.RECORD_STATUS_ACTIVE),
//                criteriaBuilder.equal(airlineJoin.get("audit").get("recordStatus"), Audit.RECORD_STATUS_ACTIVE)
//        );
//
//        return getSession().createQuery(query).getResultStream().findFirst();
//    }
//
//    @Transactional
//    public void saveOrUpdate(Flight flight) {
//        getSession().merge(flight);
//        getSession().flush();
//    }
//
//    public List<Flight> findAll(Long airlineId) {
//        Session session = getSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Flight> query = criteriaBuilder.createQuery(Flight.class);
//        Root<Flight> flightRoot = query.from(Flight.class);
//
//        // Join with the airline entity
//        Join<Flight, Airline> airlineJoin = flightRoot.join("airline");
//
//        // Apply condition for the airlineId if it is not null
//        if (airlineId != null) {
//            query.where(
//                    criteriaBuilder.equal(airlineJoin.get("airlineId"), airlineId),
//                    criteriaBuilder.equal(flightRoot.get("audit").get("recordStatus"), Audit.RECORD_STATUS_ACTIVE),
//                    criteriaBuilder.equal(airlineJoin.get("audit").get("recordStatus"), Audit.RECORD_STATUS_ACTIVE)
//            );
//        } else {
//            query.where(
//                    criteriaBuilder.equal(flightRoot.get("audit").get("recordStatus"), Audit.RECORD_STATUS_ACTIVE),
//                    criteriaBuilder.equal(airlineJoin.get("audit").get("recordStatus"), Audit.RECORD_STATUS_ACTIVE)
//            );
//        }
//
//        return session.createQuery(query).getResultList();
//    }
}
