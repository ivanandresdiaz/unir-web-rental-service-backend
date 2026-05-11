package com.unir.rental.vehicle.operation.services;

import com.unir.rental.vehicle.operation.config.VehicleClient;
import com.unir.rental.vehicle.operation.dto.request.RentalRequest;
import com.unir.rental.vehicle.operation.dto.response.RentalResponse;
import com.unir.rental.vehicle.operation.entities.RentalEntity;
import com.unir.rental.vehicle.operation.repositories.CustomerRepository;
import com.unir.rental.vehicle.operation.repositories.RentalRepository;
import com.unir.rental.vehicle.operation.repositories.RentalStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final RentalStatusRepository rentalStatusRepository;
    private final VehicleClient vehicleClient;

    public RentalResponse createRental(RentalRequest request) {

        var customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Boolean available = vehicleClient.isVehicleAvailable(
                request.getVehicleId()
        );

        if (!Boolean.TRUE.equals(available)) {
            throw new RuntimeException("Vehicle not available");
        }

        var vehicle = vehicleClient.getVehicleById(
                request.getVehicleId()
        );

        var status = rentalStatusRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Rental status not found"));

        long days = java.time.Duration.between(
                request.getStartDate(),
                request.getEndDate()
        ).toDays();

        if (days <= 0) {
            days = 1;
        }

        double totalPrice = vehicle.getDailyPrice() * days;

        var rental = RentalEntity.builder()
                .consumer(customer)
                .vehicleId(String.valueOf(vehicle.getId()))
                .vehicleModel(vehicle.getModel())
                .vehiclePlate(vehicle.getPlate())
                .status(status)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .dailyPrice(vehicle.getDailyPrice())
                .totalPrice(totalPrice)
                .build();

        var savedRental = rentalRepository.save(rental);

        var test = vehicleClient.updateVehicleStatus(vehicle.getId(), 2L);
        System.out.println(test);
        return RentalResponse.fromEntity(savedRental);
    }



    public RentalResponse getRentalById(Long rentalId) {

        var rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        return RentalResponse.fromEntity(rental);
    }

    public RentalResponse cancelRental(Long rentalId) {

        var rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        var cancelledStatus = rentalStatusRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Cancelled status not found"));

        rental.setStatus(cancelledStatus);
        rental.setUpdatedAt(LocalDateTime.now());

        var updatedRental = rentalRepository.save(rental);

        vehicleClient.updateVehicleStatus(
                Long.valueOf(rental.getVehicleId()),
                1L
        );

        return RentalResponse.fromEntity(updatedRental);
    }
}
