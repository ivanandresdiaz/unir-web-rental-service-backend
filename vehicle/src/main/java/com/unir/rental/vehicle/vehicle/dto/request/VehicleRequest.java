package com.unir.rental.vehicle.vehicle.dto.request;

import com.unir.rental.vehicle.vehicle.entities.StatusEntity;
import com.unir.rental.vehicle.vehicle.entities.VehicleEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehicleRequest {
    private String brand;
    private String model;
    private String color;
    private String plate;
    private Boolean active;
    private Double dailyPrice;
    private Long statusId;


    public VehicleEntity toEntity(StatusEntity statusEntity){
        return VehicleEntity.builder()
                .brand(brand)
                .model(model)
                .color(color)
                .plate(plate)
                .active(active)
                .dailyPrice(dailyPrice)
                .status(statusEntity)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
