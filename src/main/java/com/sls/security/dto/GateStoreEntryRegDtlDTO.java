package com.sls.security.dto;

public class GateStoreEntryRegDtlDTO {
	
	private long dtlId;
	private long hdrId;
	private long srlNo;
	private String item;
	private long quantity;
	private String dept;
	private String openClose;
	private String uom;
	private String itemCode;
	private String itemDesc;
	private long reqQuantity;
	private String advisedJuteType;
	private String actualJuteType;
	private String advisedQuality;
	private String actualQuality;
	private float advisedQuantity;
	private float actualQuantity;
	private String receivedIn;
	private String remarks;
	private String poId;
	private double weight;
	private long vehicleType;
	
	
	public long getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(long vehicleType) {
		this.vehicleType = vehicleType;
	}
	public long getDtlId() {
		return dtlId;
	}
	public void setDtlId(long dtlId) {
		this.dtlId = dtlId;
	}
	public long getHdrId() {
		return hdrId;
	}
	public void setHdrId(long hdrId) {
		this.hdrId = hdrId;
	}
	public long getSrlNo() {
		return srlNo;
	}
	public void setSrlNo(long srlNo) {
		this.srlNo = srlNo;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getOpenClose() {
		return openClose;
	}
	public void setOpenClose(String openClose) {
		this.openClose = openClose;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	
	public String getPoId() {
		return poId;
	}
	public void setPoId(String poId) {
		this.poId = poId;
	}
	@Override
	public String toString() {
		return "GateStoreEntryRegDtlDTO [dtlId=" + dtlId + ", hdrId=" + hdrId + ", srlNo=" + srlNo + ", item=" + item
				+ ", quantity=" + quantity + ", dept=" + dept + ", openClose=" + openClose + ", uom=" + uom
				+ ", itemCode=" + itemCode + ", itemDesc=" + itemDesc + ", reqQuantity=" + reqQuantity + "]";
	}
	public GateStoreEntryRegDtlDTO(long dtlId, long hdrId, long srlNo, String item, long quantity, String dept,
			String openClose, String uom, String itemCode, String itemDesc, long reqQuantity) {
		super();
		this.dtlId = dtlId;
		this.hdrId = hdrId;
		this.srlNo = srlNo;
		this.item = item;
		this.quantity = quantity;
		this.dept = dept;
		this.openClose = openClose;
		this.uom = uom;
		this.itemCode = itemCode;
		this.itemDesc = itemDesc;
		this.reqQuantity = reqQuantity;
	}
	public GateStoreEntryRegDtlDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAdvisedJuteType() {
		return advisedJuteType;
	}
	public void setAdvisedJuteType(String advisedJuteType) {
		this.advisedJuteType = advisedJuteType;
	}
	public String getActualJuteType() {
		return actualJuteType;
	}
	public void setActualJuteType(String actualJuteType) {
		this.actualJuteType = actualJuteType;
	}
	public String getAdvisedQuality() {
		return advisedQuality;
	}
	public void setAdvisedQuality(String advisedQuality) {
		this.advisedQuality = advisedQuality;
	}
	public String getActualQuality() {
		return actualQuality;
	}
	public void setActualQuality(String actualQuality) {
		this.actualQuality = actualQuality;
	}
	public float getAdvisedQuantity() {
		return advisedQuantity;
	}
	public void setAdvisedQuantity(float advisedQuantity) {
		this.advisedQuantity = advisedQuantity;
	}
	public float getActualQuantity() {
		return actualQuantity;
	}
	public void setActualQuantity(float actualQuantity) {
		this.actualQuantity = actualQuantity;
	}
	
	
	public String getReceivedIn() {
		return receivedIn;
	}
	public void setReceivedIn(String receivedIn) {
		this.receivedIn = receivedIn;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	

}
