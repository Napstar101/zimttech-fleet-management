package org.zimttech.fleetmanager.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zimttech.fleetmanager.enums.Role;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String surname;

    private String username;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="driver")
    List<Trip> driverTrips;

    @CreationTimestamp
    private OffsetDateTime creationTime;

    @UpdateTimestamp
    private OffsetDateTime lastModifiedTime;



}
