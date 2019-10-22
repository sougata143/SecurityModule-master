package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.DepartmentDTO;
import com.sls.security.dto.ItemDTO1;
import com.sls.security.dto.ItemGroupDTO;
import com.sls.security.services.serviceImpl.ItemMasterServiceImpl;

@RestController
@RequestMapping("itemmaster/")
@CrossOrigin(origins = USER_HOST_SERVER)
public class ItemMasterController {
	
	@Autowired
	ItemMasterServiceImpl itemService;
	
	/*@GetMapping("getAllItems")
	public List<ItemDTO1> getAllItems() {
		return itemService.getAllItems();
	}
	
	@GetMapping("getAllStoreItems")
	public List<ItemDTO1> getAllStoreItems() {
		return itemService.getItemsNonJute();
	}*/
	
	@GetMapping("getAllStoreItemGroups")
	public List<ItemGroupDTO> getAllStoreItemGroups() {
		return itemService.getAllStoreItemGroup();
	}
	
	/*@GetMapping("getAllDepartments")
	public List<DepartmentDTO> getAllDepartments() {
		return itemService.getAllDepartments();
	}*/

}
