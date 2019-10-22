package com.sls.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FINANCIAL_YEAR")
public class FinancialYear {

	@Id
	@Column(name="FIN_ID")
	private long finId;
	
	@Column(name="FIN_YEAR")
	private String finYear;

	public long getFinId() {
		return finId;
	}

	public void setFinId(long finId) {
		this.finId = finId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	@Override
	public String toString() {
		return "FinancialYear [finId=" + finId + ", finYear=" + finYear + "]";
	}

	public FinancialYear(long finId, String finYear) {
		super();
		this.finId = finId;
		this.finYear = finYear;
	}

	public FinancialYear() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
