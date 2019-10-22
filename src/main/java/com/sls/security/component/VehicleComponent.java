package com.sls.security.component;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.Vehicle;
import com.sls.security.repository.VehicleRepository;

@Component
public class VehicleComponent {

	@Autowired
	VehicleRepository vehicleRepo;
	
	@Transactional
	public Vehicle getVehilceById(Long id) {
		return vehicleRepo.findOne(id);
	}
	
	@Transactional
	public Vehicle getVehilceByVehicleType(String vehicletype) {
		return vehicleRepo.findByVehicleType(vehicletype);
	}
	
}
