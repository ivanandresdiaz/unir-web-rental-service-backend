package com.unir.rental.vehicle.vehicle.controller;

import com.unir.rental.vehicle.vehicle.dto.request.VehicleRequest;
import com.unir.rental.vehicle.vehicle.dto.response.VehicleResponse;
import com.unir.rental.vehicle.vehicle.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "api/v1/vehicle")
@RequiredArgsConstructor
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/")
    public ResponseEntity<VehicleResponse> saveVehicle(@RequestBody final VehicleRequest vehicleRequest){
        return ResponseEntity.ok(
                vehicleService.saveVehicle(vehicleRequest)
        );
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable final Long vehicleId){
        return ResponseEntity.ok(
                vehicleService.getVehicleById(vehicleId)
        );
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable final Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable final Long vehicleId,
                                                         @RequestBody final VehicleRequest vehicleRequest){
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId,vehicleRequest));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> searchVehicles(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String statusCode) {

        return ResponseEntity.ok(
                vehicleService.searchVehicles(brand, model, statusCode)
        );
    }

    @GetMapping("/{vehicleId}/availability")
    public ResponseEntity<Boolean> isVehicleAvailable(
            @PathVariable Long vehicleId) {

        return ResponseEntity.ok(
                vehicleService.isVehicleAvailable(vehicleId)
        );
    }

    @PutMapping("{vehicleId}/status/{statusId}")
    ResponseEntity<VehicleResponse> updateVehicleStatus(
            @PathVariable("vehicleId") Long vehicleId,
            @PathVariable("statusId") Long statusId
    ) {
        return ResponseEntity.ok(
                vehicleService.updateVehicleStatus(vehicleId, statusId)
        );
    }

}
