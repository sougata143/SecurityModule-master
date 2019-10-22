package com.sls.security.dto;

public class MukamNameBySuppDTO {

	private String mukamName;
	private long mukamId;
	public String getMukamName() {
		return mukamName;
	}
	public void setMukamName(String mukamName) {
		this.mukamName = mukamName;
	}
	public long getMukamId() {
		return mukamId;
	}
	public void setMukamId(long mukamId) {
		this.mukamId = mukamId;
	}
	@Override
	public String toString() {
		return "MukamNameBySuppDTO [mukamName=" + mukamName + ", mukamId=" + mukamId + "]";
	}
	
	
	
	
}
