package com.sls.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="FINISHING_DISPATCH_REG_DTL")
public class FinishingDispatchEntryDtl {
	
	@Id
	@Column(name="DTL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FINISHING_DISPATCH_REG_DTL_SEQ")
	@SequenceGenerator(name="FINISHING_DISPATCH_REG_DTL_SEQ", sequenceName="FINISHING_DISPATCH_REG_DTL_SEQ")
	private long dtlId;
	
	@Column(name="HDR_ID")
	private long hdrId;
	
	@Column(name="QUALITY")
	private String quality;
	
	@Column(name="QUALITY_DESC")
	private String qualityDesc;
	
	@Column(name="QUANTITY")
	private long quantity;
	
	@Column(name="UOM")
	private String unitId;
	
	@Column(name="SRL_NO")
	private long srlNo;
	
	@Column(name="OPEN_CLOSE")
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

	@Override
	public String toString() {
		return "FinishingDispatchEntryDtl [dtlId=" + dtlId + ", hdrId=" + hdrId + ", quality=" + quality + ", qualityDesc="
				+ qualityDesc + ", quantity=" + quantity + ", unitId=" + unitId + ", srlNo=" + srlNo + ", openClose="
				+ openClose + "]";
	}

	public FinishingDispatchEntryDtl(long dtlId, long hdrId, String quality, String qualityDesc, long quantity, String unitId,
			long srlNo, String openClose) {
		super();
		this.dtlId = dtlId;
		this.hdrId = hdrId;
		this.quality = quality;
		this.qualityDesc = qualityDesc;
		this.quantity = quantity;
		this.unitId = unitId;
		this.srlNo = srlNo;
		this.openClose = openClose;
	}

	public FinishingDispatchEntryDtl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
