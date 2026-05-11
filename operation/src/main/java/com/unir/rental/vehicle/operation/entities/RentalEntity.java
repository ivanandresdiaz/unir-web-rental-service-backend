package com.unir.rental.vehicle.operation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "rental")
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity consumer;
    @Column(name="vehicle_id")
    private String vehicleId;
    @Column(name="vehicle_model")
    private String vehicleModel;
    @Column(name="vehicle_plate")
    private String vehiclePlate;
    @ManyToOne
    @JoinColumn(name = "rental_status_id")
    private RentalStatusEntity status;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "daily_price")
    private Double dailyPrice;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
