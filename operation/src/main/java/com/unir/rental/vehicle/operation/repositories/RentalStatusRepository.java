package com.unir.rental.vehicle.operation.repositories;

import com.unir.rental.vehicle.operation.entities.RentalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalStatusRepository extends JpaRepository<RentalStatusEntity, Long> {
}
