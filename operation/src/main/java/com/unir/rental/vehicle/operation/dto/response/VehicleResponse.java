package com.unir.rental.vehicle.operation.dto.response;

import lombok.Getter;

@Getter
public class VehicleResponse {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String plate;
    private Double dailyPrice;
    private String status;
    private Boolean active;
}
