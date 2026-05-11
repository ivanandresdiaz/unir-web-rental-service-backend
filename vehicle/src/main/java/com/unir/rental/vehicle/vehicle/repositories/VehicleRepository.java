package com.unir.rental.vehicle.vehicle.repositories;

import com.unir.rental.vehicle.vehicle.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,Long> {

    List<VehicleEntity> findByActiveTrue();

    List<VehicleEntity> findByBrandIgnoreCaseAndActiveTrue(String brand);

    List<VehicleEntity> findByModelIgnoreCaseAndActiveTrue(String model);

    List<VehicleEntity> findByStatus_CodeAndActiveTrue(String code);

}
