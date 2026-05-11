package com.unir.rental.vehicle.operation.repositories;

import com.unir.rental.vehicle.operation.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Long>  {
}
