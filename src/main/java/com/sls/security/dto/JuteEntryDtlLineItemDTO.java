package com.sls.security.dto;

public class JuteEntryDtlLineItemDTO {

	private String advisedJuteType;
	private String actualJuteType;
	private String advisedQuality;
	private String actualQuality;
	private long advisedQuantity;
	private Float actualQuantity;
	private String uom;
	private String remarks;
	private Float quantity;
	private long hdrId;
	private String receivedIn;
	private String itemCode;
	private long polineitemnum;
	private int kgs;
	private String openClose;
	private long dtlId;
	private boolean isPOAmment;
	private long vehicleType;
	
	
	
	public long getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(long vehicleType) {
		this.vehicleType = vehicleType;
	}
	public boolean getIsPOAmment() {
		return isPOAmment;
	}
	public void setIsPOAmment(boolean isPOAmment) {
		this.isPOAmment = isPOAmment;
	}
	public long getDtlId() {
		return dtlId;
	}
	public void setDtlId(long dtlId) {
		this.dtlId = dtlId;
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
	public long getAdvisedQuantity() {
		return advisedQuantity;
	}
	public void setAdvisedQuantity(long advisedQuantity) {
		this.advisedQuantity = advisedQuantity;
	}
	public Float getActualQuantity() {
		return actualQuantity;
	}
	public void setActualQuantity(Float actualQuantity) {
		this.actualQuantity = actualQuantity;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	
	
	
	public long getHdrId() {
		return hdrId;
	}
	public void setHdrId(long hdrId) {
		this.hdrId = hdrId;
	}
	public String getReceivedIn() {
		return receivedIn;
	}
	public void setReceivedIn(String receivedIn) {
		this.receivedIn = receivedIn;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public long getPolineitemnum() {
		return polineitemnum;
	}
	public void setPolineitemnum(long polineitemnum) {
		this.polineitemnum = polineitemnum;
	}
	public int getKgs() {
		return kgs;
	}
	public void setKgs(int kgs) {
		this.kgs = kgs;
	}
	public String getOpenClose() {
		return openClose;
	}
	public void setOpenClose(String openClose) {
		this.openClose = openClose;
	}
	@Override
	public String toString() {
		return "JuteEntryDtlLineItemDTO [advisedJuteTyp=" + advisedJuteType + ", actualJuteTyp=" + actualJuteType
				+ ", advisedQuality=" + advisedQuality + ", actualQuality=" + actualQuality + ", advisedQuantity="
				+ advisedQuantity + ", actualQuantity=" + actualQuantity + ", uom=" + uom + ", remarks=" + remarks
				+ ", quantity=" + quantity + "]";
	}
	public JuteEntryDtlLineItemDTO(String advisedJuteTyp, String actualJuteTyp, String advisedQuality,
			String actualQuality, long advisedQuantity, Float actualQuantity, String uom, String remarks,
			Float quantity) {
		super();
		this.advisedJuteType = advisedJuteTyp;
		this.actualJuteType = actualJuteTyp;
		this.advisedQuality = advisedQuality;
		this.actualQuality = actualQuality;
		this.advisedQuantity = advisedQuantity;
		this.actualQuantity = actualQuantity;
		this.uom = uom;
		this.remarks = remarks;
		this.quantity = quantity;
	}
	public JuteEntryDtlLineItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
