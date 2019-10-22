package com.sls.security.dto;

public class JutePriceDTO {

	private String juteQuality;

	public String getJuteQuality() {
		return juteQuality;
	}

	public void setJuteQuality(String juteQuality) {
		this.juteQuality = juteQuality;
	}

	@Override
	public String toString() {
		return "JutePriceDTO [juteQuality=" + juteQuality + "]";
	}

	public JutePriceDTO(String juteQuality) {
		super();
		this.juteQuality = juteQuality;
	}

	public JutePriceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
