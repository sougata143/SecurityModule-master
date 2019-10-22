package com.sls.security.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class GateStoreEntryRegHdrDTO {

	private long id;
	private long challanNo;
	private Date challanDate;
	private String vehicleNo;
	private String driverName;
	private String supplierName;
	private String remarks;
	private String openClose;
	/*private Date inDate;
	private Timestamp inTime;*/
	private Date inDate;
	private String inTime;
	private String outTime;
	private Date outDate;
	private String updateBy;
	private Timestamp updateDateTime;
	private List<String> poNo;
	private String suppCode;
//	private String suppAddress;
	
	private List<POLineItemDtlsDTO> polineitems;
//	private POLineItemDtlDTO polineitems;
	
	
	
	public long getChallanNo() {
		return challanNo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setChallanNo(long challanNo) {
		this.challanNo = challanNo;
	}
	public Date getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(Date challanDate) {
		this.challanDate = challanDate;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOpenClose() {
		return openClose;
	}
	public void setOpenClose(String openClose) {
		this.openClose = openClose;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	/*public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}*/
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public List<String> getPoNo() {
		return poNo;
	}
	public void setPoNo(List<String> poNo) {
		this.poNo = poNo;
	}
	public String getSuppCode() {
		return suppCode;
	}
	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}
	
	/*public String getSuppAddress() {
		return suppAddress;
	}
	public void setSuppAddress(String suppAddress) {
		this.suppAddress = suppAddress;
	}*/
	@Override
	public String toString() {
		return "GateStoreEntryRegHdrDTO [hdrId=" + id + ", challanNo=" + challanNo + ", challanDate=" + challanDate
				+ ", vehicleNo=" + vehicleNo + ", driverName=" + driverName + ", supplierName=" + supplierName
				+ ", remarks=" + remarks + ", openClose=" + openClose + ", inDate=" + inDate + ", inTime=" + inTime
				+ ", outTime=" + outTime + ", outDate=" + outDate + ", updateBy=" + updateBy + ", updateDateTime="
				+ updateDateTime + ", poNo=" + poNo + ", suppCode=" + suppCode + "]";
	}
	/*public GateStoreEntryRegHdrDTO(long hdrId, long challanNo, Date challanDate, String vehicleNo, String driverName,
			String supplierName, String remarks, String openClose, Date inDate, Timestamp inTime, Timestamp outTime,
			Date outDate, String updateBy, Timestamp updateDateTime, String poNo, String suppCode) {
		super();
		this.hdrId = hdrId;
		this.challanNo = challanNo;
		this.challanDate = challanDate;
		this.vehicleNo = vehicleNo;
		this.driverName = driverName;
		this.supplierName = supplierName;
		this.remarks = remarks;
		this.openClose = openClose;
		this.inDate = inDate;
		this.inTime = inTime;
		this.outTime = outTime;
		this.outDate = outDate;
		this.updateBy = updateBy;
		this.updateDateTime = updateDateTime;
		this.poNo = poNo;
		this.suppCode = suppCode;
	}*/
	public GateStoreEntryRegHdrDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<POLineItemDtlsDTO> getPolineitems() {
		return polineitems;
	}
	public void setPolineitems(List<POLineItemDtlsDTO> polineitems) {
		this.polineitems = polineitems;
	}
	/*public POLineItemDtlDTO getPolineitems() {
		return polineitems;
	}
	public void setPolineitems(POLineItemDtlDTO polineitems) {
		this.polineitems = polineitems;
	}*/
	
	
	
	
}
