package com.sls.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name="SELECTION_QUALITY_TYPE_MST")
public class SelectQualityTypeMst {
	
	@Id
	@Column(name="REC_ID")
	private long recId;
	
	@Column(name="QUALITY_TYPE")
	private String qualityType;

}
