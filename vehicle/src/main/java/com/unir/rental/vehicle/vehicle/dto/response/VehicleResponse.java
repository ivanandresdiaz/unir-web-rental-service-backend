package com.unir.rental.vehicle.vehicle.dto.response;

import com.unir.rental.vehicle.vehicle.entities.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String plate;
    private Double dailyPrice;
    private String status;
    private Boolean active;
    private LocalDateTime createdAt;


    public static VehicleResponse fromEntity( VehicleEntity vehicleEntity){
        return VehicleResponse.builder()
                .id(vehicleEntity.getId())
                .brand(vehicleEntity.getBrand())
                .model(vehicleEntity.getModel())
                .color(vehicleEntity.getColor())
                .plate(vehicleEntity.getPlate())
                .dailyPrice(vehicleEntity.getDailyPrice())
                .active(vehicleEntity.getActive())
                .status(vehicleEntity.getStatus().getName())
                .createdAt(vehicleEntity.getCreatedAt())
                .build();
    }
}
