package org.zimttech.fleetmanager.service.ifaces;

import org.zimttech.fleetmanager.domain.User;
import org.zimttech.fleetmanager.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User registerUser(UserDto userDto);
}
