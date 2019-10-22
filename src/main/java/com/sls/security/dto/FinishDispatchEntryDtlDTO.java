package com.sls.security.dto;

import javax.persistence.Column;

public class FinishDispatchEntryDtlDTO {

	private long dtlId;
	private long hdrId;
	private String quality;
	private String qualityDesc;
	private long quantity;
	private String unitId;
	private long srlNo;
	private String openClose;

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

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getQualityDesc() {
		return qualityDesc;
	}

	public void setQualityDesc(String qualityDesc) {
		this.qualityDesc = qualityDesc;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public long getSrlNo() {
		return srlNo;
	}

	public void setSrlNo(long srlNo) {
		this.srlNo = srlNo;
	}

	public String getOpenClose() {
		return openClose;
	}

	public void setOpenClose(String openClose) {
		this.openClose = openClose;
	}
	
}
