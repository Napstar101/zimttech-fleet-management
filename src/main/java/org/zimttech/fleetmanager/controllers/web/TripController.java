package org.zimttech.fleetmanager.controllers.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zimttech.fleetmanager.service.ifaces.TripService;

@RequestMapping("trips")
@RequiredArgsConstructor
@Slf4j
@Controller
public class TripController {
    private final TripService tripService;
}
