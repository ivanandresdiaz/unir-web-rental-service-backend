package com.unir.rental.vehicle.vehicle.services;

import com.unir.rental.vehicle.vehicle.dto.request.VehicleRequest;
import com.unir.rental.vehicle.vehicle.dto.response.VehicleResponse;
import com.unir.rental.vehicle.vehicle.entities.StatusEntity;
import com.unir.rental.vehicle.vehicle.entities.VehicleEntity;
import com.unir.rental.vehicle.vehicle.repositories.StatusRepository;
import com.unir.rental.vehicle.vehicle.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final StatusRepository statusRepository;
    private final VehicleRepository vehicleRepository;


    public StatusEntity getStatusById(final Long id){

        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }

    public VehicleResponse saveVehicle(VehicleRequest vehicleRequest){
      try{
          var status = getStatusById(vehicleRequest.getStatusId());

        return VehicleResponse.fromEntity(
                vehicleRepository.save(
                        vehicleRequest.toEntity(status)
                )
        );
      } catch (DataAccessException dae) {
          throw new RuntimeException(dae);
      }
    }


    public VehicleResponse getVehicleById(final Long id){
        return VehicleResponse.fromEntity(
                vehicleRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Vehicle with "+ id + " not fount"))
        );
    }


    public void deleteVehicle(Long vehicleId) {
        var vehicle = getVehicleEntity(vehicleId);

        vehicle.setActive(false);
        vehicle.setUpdatedAt(LocalDateTime.now());

        vehicleRepository.save(vehicle);
    }

    public VehicleResponse updateVehicle(Long vehicleId, VehicleRequest vehicleRequest) {

        var vehicle = getVehicleEntity(vehicleId);
        var status =  getStatusById(vehicleRequest.getStatusId());

        vehicle.setBrand(vehicleRequest.getBrand());
        vehicle.setModel(vehicleRequest.getModel());
        vehicle.setColor(vehicleRequest.getColor());
        vehicle.setPlate(vehicleRequest.getPlate());
        vehicle.setDailyPrice(vehicleRequest.getDailyPrice());
        vehicle.setStatus(status);
        vehicle.setUpdatedAt(LocalDateTime.now());

        return VehicleResponse.fromEntity(vehicleRepository.save(vehicle));
    }


    public VehicleEntity getVehicleEntity(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public List<VehicleResponse> searchVehicles(
            String brand,
            String model,
            String statusCode) {

        List<VehicleEntity> vehicles;

        if (brand != null) {
            vehicles = vehicleRepository.findByBrandIgnoreCaseAndActiveTrue(brand);
        } else if (model != null) {
            vehicles = vehicleRepository.findByModelIgnoreCaseAndActiveTrue(model);
        } else if (statusCode != null) {
            vehicles = vehicleRepository.findByStatus_CodeAndActiveTrue(statusCode);
        } else {
            vehicles = vehicleRepository.findByActiveTrue();
        }

        return vehicles.stream()
                .map(VehicleResponse::fromEntity)
                .toList();
    }

    public Boolean isVehicleAvailable(Long vehicleId) {
        VehicleEntity vehicle = getVehicleEntity(vehicleId);

        return Boolean.TRUE.equals(vehicle.getActive())
                && "AVA".equals(vehicle.getStatus().getCode());
    }

    public VehicleResponse updateVehicleStatus(Long vehicleId, Long statusId) {

        var vehicle = getVehicleEntity(vehicleId);

        var status = getStatusById(statusId);

        vehicle.setStatus(status);
        vehicle.setUpdatedAt(LocalDateTime.now());

        return VehicleResponse.fromEntity(
                vehicleRepository.save(vehicle)
        );
    }
}
