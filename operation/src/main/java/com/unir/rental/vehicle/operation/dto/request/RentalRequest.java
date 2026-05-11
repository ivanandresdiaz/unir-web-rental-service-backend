package com.unir.rental.vehicle.operation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RentalRequest {
    private Long customerId;
    private Long vehicleId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
