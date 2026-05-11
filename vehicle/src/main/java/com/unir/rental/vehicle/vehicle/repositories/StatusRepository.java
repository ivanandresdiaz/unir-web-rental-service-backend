package com.unir.rental.vehicle.vehicle.repositories;

import com.unir.rental.vehicle.vehicle.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
