package com.unir.rental.vehicle.operation.controller;


import com.unir.rental.vehicle.operation.dto.request.RentalRequest;
import com.unir.rental.vehicle.operation.dto.response.RentalResponse;
import com.unir.rental.vehicle.operation.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/operation")
@RequiredArgsConstructor
@RestController
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/rent")
    public ResponseEntity<RentalResponse> createRental(
            @RequestBody final RentalRequest request) {

        return ResponseEntity.ok(
                rentalService.createRental(request)
        );
    }


    @GetMapping("/rent/{rentalId}")
    public ResponseEntity<RentalResponse> getRentalById(
            @PathVariable Long rentalId) {

        return ResponseEntity.ok(
                rentalService.getRentalById(rentalId)
        );
    }

    @PutMapping("/rent/{rentalId}/cancel")
    public ResponseEntity<RentalResponse> cancelRental(
            @PathVariable Long rentalId) {

        return ResponseEntity.ok(
                rentalService.cancelRental(rentalId)
        );
    }
}
