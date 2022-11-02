package org.zimttech.fleetmanager.controllers.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zimttech.fleetmanager.domain.Trip;
import org.zimttech.fleetmanager.domain.User;
import org.zimttech.fleetmanager.dto.UserDto;
import org.zimttech.fleetmanager.service.ifaces.UserService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> postUserRegistrationRequest(@RequestBody UserDto userDto) {
          return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
    }

    //Login API is already implemented by Spring Security and configured in the class: SecurityConfig
}
