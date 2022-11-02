package org.zimttech.fleetmanager.controllers.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.zimttech.fleetmanager.domain.Trip;
import org.zimttech.fleetmanager.dto.TripRequestDto;
import org.zimttech.fleetmanager.service.ifaces.TripService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/trips", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TripRestController {
    private final TripService tripService;

    @GetMapping("/get-active")
    public ResponseEntity<List<Trip>> getCurrentActiveTrips() {
        return new ResponseEntity<>(tripService.getCurrentTrips(), HttpStatus.OK);
    }
    @PostMapping("/request-driver-trip")
    public ResponseEntity<Trip> requestDriverTrip(@RequestBody TripRequestDto tripRequestDto) {
        return new ResponseEntity<>(tripService.driverRequestTrip(tripRequestDto), HttpStatus.OK);
    }
    @GetMapping("/get-driver-trip-history/{driverId}")
    public ResponseEntity<List<Trip>> getDriverTripHistory(@PathVariable("driverId")Long driverId) {
        return new ResponseEntity<>(tripService.getDriverPastAndCurrentTrips(driverId), HttpStatus.OK);
    }
    @GetMapping("/get-new-trip-requests")
    public ResponseEntity<List<Trip>> getNewTripRequests() {
        return new ResponseEntity<>(tripService.getCurrentTrips(), HttpStatus.OK);
    }

    @PostMapping("/approve-driver-trip/{tripId}")
    public ResponseEntity<Trip> approveDriverTrip(@PathVariable("tripId")Long tripId,
                                                  @RequestBody String assignedVehicleReg) {
        return new ResponseEntity<>(tripService.approveTripRequest(tripId, assignedVehicleReg), HttpStatus.OK);
    }
    @PostMapping("/reject-driver-trip/{tripId}")
    public ResponseEntity<Trip> rejectDriverTrip(@PathVariable("tripId")Long tripId) {
        return new ResponseEntity<>(tripService.rejectTripRequest(tripId), HttpStatus.OK);
    }

    @PostMapping("/start-trip/{tripId}")
    public ResponseEntity<Trip> startDriverTrip(@PathVariable("tripId")Long tripId) {
        return new ResponseEntity<>(tripService.driverStartTrip(tripId), HttpStatus.OK);
    }

    @PostMapping("/end-trip/{tripId}")
    public ResponseEntity<Trip> endDriverTrip(@PathVariable("tripId")Long tripId) {
        return new ResponseEntity<>(tripService.driverEndTrip(tripId), HttpStatus.OK);
    }


}
