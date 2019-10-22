package com.sls.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="LORRY_TYPE_MASTER")
public class Vehicle {	
	
	@Id
	@Column(name="LORRY_ID")
	private  long id;
	
	@Column(name="LORRY_TYPE")
	private  String vehicleType ;
	
	@Column(name="WEIGHT")
	private  String weight ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	

}
