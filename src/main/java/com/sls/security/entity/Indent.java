package com.sls.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity()

@Table(name="SCM_INDENT_LINE_ITEM")
public class Indent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INDENT_ITM_SEQ")
    @SequenceGenerator(sequenceName = "scm_indent_item_seq", allocationSize = 1, name = "INDENT_ITM_SEQ")
	@Column(name="INDENT_SRL_NO", nullable=true)
	private  Long id;
	
	@Column(name="INDENT_NO", nullable=true)
	private  String indentHeaderId;
	
	@Column(name="INDENT_TYPE", nullable=true)
	private  String type ;
	
	@Column(name="INDENT_STATUS", nullable=true)
	private  String status ;
	
	@Column(name="GROUP_CODE", nullable=true)
	private  String itemGroupId;
	
	@Column(name="ITEM_CODE", nullable=true)
	private  String itemId;
	
	@Column(name="UOM_CODE", nullable=true)
	private  String unitId;
	
	@Column(name="DEPT_CODE", nullable=true)
	private  Long deptId;
	
	@Column(name="STOCK_IN_HAND", nullable=true)
	private Float stock;
	
	@Column(name="INDENT_QTY", nullable=true)
	private Long indentQuantity;
	
	@Column(name="CANCEL_QTY", nullable=true)
	private Long indentCancelledQuantity;
	
	@Column(name="APPROVER_LEVEL_FIRST", nullable=true)
	private  String approverFirst ;
	
	@Column(name="APPROVER_LEVEL_SECOND", nullable=true)
	private  String approverSecond ;
	
	@Column(name="APPROVE_LEVEL_FIRST_DATE", nullable=true)
	private  Date approveFirstDate ;
	
	@Column(name="APPROVE_LEVEL_SECOND_DATE", nullable=true)
	private  Date approveSecondDate ;
	
	@Column(name="QUALITY", nullable=true)
	private  Long qualityCode;
	
	@Column(name="MARKA", nullable=true)
	private  String marka;
	
	@Column(name="ADDITIONAL_REQUIREMENT", nullable=true)
	private  String additionalRequirement;
	
	

	public String getAdditionalRequirement() {
		return additionalRequirement;
	}

	public void setAdditionalRequirement(String additionalRequirement) {
		this.additionalRequirement = additionalRequirement;
	}

	public long getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(long qualityCode) {
		this.qualityCode = qualityCode;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getApproverFirst() {
		return approverFirst;
	}

	public void setApproverFirst(String approverFirst) {
		this.approverFirst = approverFirst;
	}

	public String getApproverSecond() {
		return approverSecond;
	}

	public void setApproverSecond(String approverSecond) {
		this.approverSecond = approverSecond;
	}

	public Date getApproveFirstDate() {
		return approveFirstDate;
	}

	public void setApproveFirstDate(Date approveFirstDate) {
		this.approveFirstDate = approveFirstDate;
	}

	public Date getApproveSecondDate() {
		return approveSecondDate;
	}

	public void setApproveSecondDate(Date approveSecondDate) {
		this.approveSecondDate = approveSecondDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIndentHeaderId() {
		return indentHeaderId;
	}

	public void setIndentHeaderId(String indentHeaderId) {
		this.indentHeaderId = indentHeaderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemGroupId() {
		return itemGroupId;
	}

	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public long getIndentQuantity() {
		return indentQuantity;
	}

	public void setIndentQuantity(long indentQuantity) {
		this.indentQuantity = indentQuantity;
	}

	public long getIndentCancelledQuantity() {
		return indentCancelledQuantity;
	}

	public void setIndentCancelledQuantity(long indentCancelledQuantity) {
		this.indentCancelledQuantity = indentCancelledQuantity;
	}

	

	

}
