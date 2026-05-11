package com.unir.rental.vehicle.operation.config;

import com.unir.rental.vehicle.operation.dto.response.VehicleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "vehicle" )
public interface VehicleClient {
    @GetMapping("/api/v1/vehicle/{vehicleId}/availability")
    Boolean isVehicleAvailable(
            @PathVariable("vehicleId") Long vehicleId
    );

    @GetMapping("/api/v1/vehicle/{vehicleId}")
    VehicleResponse getVehicleById(
            @PathVariable("vehicleId") Long vehicleId
    );

    @PutMapping("/api/v1/vehicle/{vehicleId}/status/{statusId}")
    VehicleResponse updateVehicleStatus(
            @PathVariable("vehicleId") Long vehicleId,
            @PathVariable("statusId") Long statusId
    );
}
