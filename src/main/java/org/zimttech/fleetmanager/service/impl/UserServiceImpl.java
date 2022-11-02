package org.zimttech.fleetmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zimttech.fleetmanager.domain.User;
import org.zimttech.fleetmanager.dto.UserDto;
import org.zimttech.fleetmanager.enums.Role;
import org.zimttech.fleetmanager.repository.UserRepository;
import org.zimttech.fleetmanager.service.ifaces.UserService;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User registerUser(UserDto userDto) {
        try{
            User user = User.builder()
                    .firstName(userDto.getFirstName())
                    .surname(userDto.getSurname())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .creationTime(OffsetDateTime.now())
                    .role(Role.valueOf(userDto.getRole()))
                    .lastModifiedTime(OffsetDateTime.now())
                    .build();
            return userRepository.save(user);
        }catch (Exception ex){
            log.error("Failed to register user!!" +  ex.getMessage());
            ex.printStackTrace();
            return null;
        }

    }
}
