package org.zimttech.fleetmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zimttech.fleetmanager.enums.TripStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TripRequestDto {

    private String destination;

    private BigDecimal distance;

    private Long driverId;

    private LocalDate startDate;

    private LocalDate expectedEndDate;
}
