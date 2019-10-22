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

import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table(name="SCM_INDENT_HDR")

public class IndentHeader {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INDENT_NO_SEQ")
	@SequenceGenerator(name="INDENT_NO_SEQ", sequenceName="INDENT_NO_SEQ")
    @Column(name="INDENT_NO", nullable=true)
	private  String id;
	
	@Column(name="INDENT_TYPE", nullable=true)
	private  String type ;
	
	@Column(name="INDENT_STATUS", nullable=true)
	private  String status ;
	
	@Column(name="USER_1", nullable=true)
	private  String submitter ;
	
	@Column(name="FIN_YEAR", nullable=true)
	private  String finnacialYear ;
	
	@Column(name="CREATE_DATE", nullable=true)
	private  Date createDate ;
	
	@Column(name="INDENT_DATE", nullable=true)
	private  Date indentDate ;
	
	@Column(name="APPROVER_LEVEL_FIRST", nullable=true)
	private  String approverFirst ;
	
	@Column(name="APPROVER_LEVEL_SECOND", nullable=true)
	private  String approverSecond ;
	
	@Column(name="APPROVE_LEVEL_FIRST_DATE", nullable=true)
	private  Date approveFirstDate ;
	
	@Column(name="APPROVE_LEVEL_SECOND_DATE", nullable=true)
	private  Date approveSecondDate ;
	
	@Column(name="MUKAM", nullable=true)
	private  String mukam ;
	
	@Column(name="VEHICLE_TYPE_ID", nullable=true)
	private  Long vehicleTypeId ;
	
	@Column(name="VEHICLE_QUANTITY", nullable=true)
	private  Long vehicleQuantity ;
	
	

	public String getMukam() {
		return mukam;
	}

	public void setMukam(String mukam) {
		this.mukam = mukam;
	}

	public long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public long getVehicleQuantity() {
		return vehicleQuantity;
	}

	public void setVehicleQuantity(long vehicleQuantity) {
		this.vehicleQuantity = vehicleQuantity;
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

	public Date getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
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

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFinnacialYear() {
		return finnacialYear;
	}

	public void setFinnacialYear(String finnacialYear) {
		this.finnacialYear = finnacialYear;
	}

}
