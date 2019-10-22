package com.sls.security.dto;

public class POLineItemDtlDTO {
	
	private String poId;
	private long hdrIdDtl;
	private String itemId;
	private String itemDesc;
	private long reqQuantity;
	private String dept;
	private String unitId;
	private long quantity;
	private long dtlId;
	
	
	
	public String getPoId() {
		return poId;
	}
	public void setPoId(String poId) {
		this.poId = poId;
	}
	public long getDtlId() {
		return dtlId;
	}
	public void setDtlId(long dtlId) {
		this.dtlId = dtlId;
	}
	public long getHdrIdDtl() {
		return hdrIdDtl;
	}
	public void setHdrIdDtl(long hdrIdDtl) {
		this.hdrIdDtl = hdrIdDtl;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public long getReqQuantity() {
		return reqQuantity;
	}
	public void setReqQuantity(long reqQuantity) {
		this.reqQuantity = reqQuantity;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	

}
