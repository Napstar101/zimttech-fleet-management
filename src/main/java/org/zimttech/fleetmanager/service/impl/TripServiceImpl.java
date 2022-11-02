package org.zimttech.fleetmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zimttech.fleetmanager.domain.Trip;
import org.zimttech.fleetmanager.domain.User;
import org.zimttech.fleetmanager.dto.TripRequestDto;
import org.zimttech.fleetmanager.enums.TripStatus;
import org.zimttech.fleetmanager.repository.TripRepository;
import org.zimttech.fleetmanager.repository.UserRepository;
import org.zimttech.fleetmanager.service.ifaces.TripService;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Override
    public Trip driverRequestTrip(TripRequestDto tripRequestDto) {

        Optional<User> optionalDriver = userRepository.findById(tripRequestDto.getDriverId());

        return optionalDriver.map(driver->{
            Trip trip = new Trip();
            trip.setDestination(tripRequestDto.getDestination());
            trip.setStatus(TripStatus.PENDING_APPROVAL);
            trip.setDistance(tripRequestDto.getDistance());
            trip.setDriver(driver);
            trip.setIsDriverEndedTrip(Boolean.FALSE);

            return tripRepository.save(trip);
        }).orElse(null);

    }

    @Override
    public Trip approveTripRequest(Long tripId, String assignedVehicleReg) {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);

        return optionalTrip.map(trip->{
            trip.setStatus(TripStatus.APPROVED);
            trip.setAssignedRegistrationPlate(assignedVehicleReg);
            return tripRepository.save(trip);
        }).orElse(null);

    }

    @Override
    public Trip driverEndTrip(Long tripId) {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);

        return optionalTrip.map(trip->{
            trip.setIsDriverEndedTrip(Boolean.TRUE);
            trip.setActualEndDate(LocalDate.now());
            trip.setStatus(TripStatus.ENDED);
            return tripRepository.save(trip);
        }).orElse(null);
    }

    @Override
    public Trip driverStartTrip(Long tripId) {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        return optionalTrip.map(trip->{
            trip.setStatus(TripStatus.IN_PROGRESS);
            return tripRepository.save(trip);
        }).orElse(null);
    }

    @Override
    public List<Trip> getCurrentTrips() {
        return tripRepository.findAllByStatus(TripStatus.IN_PROGRESS);
    }
}
