package com.carservicecenter.demo.service;

import com.carservicecenter.demo.model.Vehicle;
import com.carservicecenter.demo.repository.VehicleRepository;
import com.carservicecenter.demo.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements VehicleDao {

    @Autowired
    VehicleRepository vehicleRepository;

    public void addVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle){
        vehicleRepository.delete(vehicle);
    }
}
