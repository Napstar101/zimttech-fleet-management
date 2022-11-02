package org.zimttech.fleetmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zimttech.fleetmanager.enums.TripStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String destination;

    private BigDecimal distance;

    private TripStatus status;

    private String assignedRegistrationPlate;

    private LocalDate startDate;

    private LocalDate expectedEndDate;

    private LocalDate actualEndDate;

    private Boolean isDriverEndedTrip;

    @ManyToOne
    private User driver;

    @CreationTimestamp
    private OffsetDateTime creationTime;

    @UpdateTimestamp
    private OffsetDateTime lastModifiedTime;
}
