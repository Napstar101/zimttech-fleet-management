package org.zimttech.fleetmanager.service.ifaces;

import org.zimttech.fleetmanager.domain.Trip;
import org.zimttech.fleetmanager.dto.TripRequestDto;

import java.util.List;

public interface TripService {
    Trip driverRequestTrip(TripRequestDto tripRequestDto);
    Trip approveTripRequest(Long tripId, String assignedVehicleReg);

    Trip driverEndTrip(Long tripId);
    Trip driverStartTrip(Long tripId);

    List<Trip> getCurrentTrips();
}
