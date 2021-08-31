package com.carservicecenter.demo.dao;

import com.carservicecenter.demo.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface VehicleDao {

    void addVehicle(Vehicle vehicle);

    void deleteVehicle(Vehicle vehicle);
}
