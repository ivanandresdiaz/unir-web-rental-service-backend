package com.unir.rental.vehicle.operation.repositories;

import com.unir.rental.vehicle.operation.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

}
