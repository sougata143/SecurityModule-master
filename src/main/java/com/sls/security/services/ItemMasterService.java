package com.sls.security.services;

import java.util.List;

import com.sls.security.dto.DepartmentDTO;
import com.sls.security.dto.ItemDTO1;
import com.sls.security.dto.ItemGroupDTO;

public interface ItemMasterService {

	public List<ItemDTO1> getAllItems();
	public List<ItemDTO1> getItemsNonJute();
	public List<ItemGroupDTO> getAllStoreItemGroup();
	public List<DepartmentDTO> getAllDepartments();
	
}
