package org.zimttech.fleetmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zimttech.fleetmanager.domain.Trip;
import org.zimttech.fleetmanager.domain.User;
import org.zimttech.fleetmanager.enums.TripStatus;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByStatus(TripStatus status);

    List<Trip> findAllByDriver(User driver);
}
