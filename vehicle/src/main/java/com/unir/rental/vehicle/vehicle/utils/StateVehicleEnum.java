package com.unir.rental.vehicle.vehicle.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateVehicleEnum {
    AVAILABLE("Disposable"),
    NOT_AVAILABLE("No Disposable");

    private final String status;
}
