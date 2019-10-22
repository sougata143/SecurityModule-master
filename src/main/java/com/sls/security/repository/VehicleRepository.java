package com.sls.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.security.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	Vehicle findByVehicleType(String vehicleType);
	
}
