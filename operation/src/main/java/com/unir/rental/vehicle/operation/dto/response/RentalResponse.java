package com.unir.rental.vehicle.operation.dto.response;

import com.unir.rental.vehicle.operation.entities.RentalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class RentalResponse {
    private Long id;
    private Long customerId;
    private Long vehicleId;
    private String vehicleModel;
    private String vehiclePlate;
    private String rentalStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal dailyPrice;
    private BigDecimal totalPrice;

    public static RentalResponse fromEntity(RentalEntity rental) {
        return RentalResponse.builder()
                .id(rental.getId())
                .customerId(rental.getConsumer().getId())
                .vehicleId(Long.valueOf(rental.getVehicleId()))
                .vehicleModel(rental.getVehicleModel())
                .vehiclePlate(rental.getVehiclePlate())
                .rentalStatus(rental.getStatus().getName())
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .dailyPrice(BigDecimal.valueOf(rental.getDailyPrice()))
                .totalPrice(BigDecimal.valueOf(rental.getTotalPrice()))
                .build();
    }
}
