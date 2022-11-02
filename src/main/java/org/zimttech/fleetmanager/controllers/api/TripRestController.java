package org.zimttech.fleetmanager.controllers.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.zimttech.fleetmanager.domain.Trip;
import org.zimttech.fleetmanager.service.ifaces.TripService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripRestController {
    private final TripService tripService;

    @GetMapping()
    public List<Trip> showLoginForm() {
        return tripService.getCurrentTrips();
    }
}
