package com.sls.security.dto;

import java.util.List;

import com.sls.security.entity.Mukam;

public class POSupplierDTO {
	
	private String suppCode;
	private String suppName;
	private String address1; 
	private Mukam mukams;
	private long brokerId;
	private String brokerName;
	
	
	public long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getSuppCode() {
		return suppCode;
	}
	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public Mukam getMukams() {
		return mukams;
	}
	public void setMukams(Mukam mukams) {
		this.mukams = mukams;
	}
	
	
	
	

}
