package com.sls.security.dto;

import java.util.List;

public class ItemGroupDTO {
	
	private  String groupCode;
	private  String groupDesc ;
	private List<DepartmentDTO> dept;
	private List<ItemDTO1> item;
	
	
	
	public List<DepartmentDTO> getDept() {
		return dept;
	}
	public void setDept(List<DepartmentDTO> dept) {
		this.dept = dept;
	}
	public List<ItemDTO1> getItem() {
		return item;
	}
	public void setItem(List<ItemDTO1> item) {
		this.item = item;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
	
	

}
