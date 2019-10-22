package com.sls.security.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.security.component.DepartmentDao;
import com.sls.security.component.ItemGroupDeptDao;
import com.sls.security.component.ItemGroupMasterDao;
import com.sls.security.component.ItemMasterDao;
import com.sls.security.dto.DepartmentDTO;
import com.sls.security.dto.ItemDTO1;
import com.sls.security.dto.ItemGroupDTO;
import com.sls.security.entity.Department;
import com.sls.security.entity.ItemGroupDept;
import com.sls.security.entity.ItemGroupMaster;
import com.sls.security.entity.ItemMaster;
import com.sls.security.services.ItemMasterService;


@Service
public class ItemMasterServiceImpl implements ItemMasterService {
	
	@Autowired
	ItemMasterDao itemDao;
	
	@Autowired
	ItemGroupMasterDao groupDao;
	
	@Autowired
	DepartmentDao deptDao;
	
	@Autowired
	ItemGroupDeptDao groupmapDao;

	@Override
	public List<ItemDTO1> getAllItems() {
		List<ItemDTO1> itemDTO = new ArrayList<>();
		
		try {
			List<ItemMaster> items = itemDao.getAllItemMaster();
			items.forEach(item->{
				ItemDTO1 itemEntity = new ItemDTO1();
				itemEntity.setId(item.getId());
				itemEntity.setItemDsc(item.getitemDsc());
				itemDTO.add(itemEntity);
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return itemDTO;
	}

	@Override
	public List<ItemDTO1> getItemsNonJute() {
		List<ItemDTO1> items = new ArrayList<>();
		try {
			List<ItemMaster> itemEntities = itemDao.getStoreItemMasterByGroupCode();
			itemEntities.forEach(item->{
				ItemDTO1 itemDTO = new ItemDTO1();
				itemDTO.setId(item.getId());
				itemDTO.setItemDsc(item.getitemDsc());
				items.add(itemDTO);
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public List<ItemGroupDTO> getAllStoreItemGroup() {
		List<ItemGroupDTO> groupDTOs = new ArrayList<>();
		try {
			List<ItemGroupMaster> groupEntities = groupDao.findItemGroupFiltered();
			groupEntities.forEach(group->{
				ItemGroupDTO groupDTO = new ItemGroupDTO();
				List<ItemGroupDept> groupdept = groupmapDao.getMapByItemGroup(group.getId());
				List<ItemMaster> items = itemDao.getItemMasterByGroupCode(group.getId());
				
				groupDTO.setGroupCode(group.getId());
				groupDTO.setGroupDesc(group.getgrpDsc());
				
				List<DepartmentDTO> deptDTOs = new ArrayList<>();
				for(int i = 0 ; i < groupdept.size() ; i++) {
					DepartmentDTO deptDTO = new DepartmentDTO();
					Department dept = deptDao.findById(groupdept.get(i).getDeptId());
					
					deptDTO.setDept(dept.getdepartmentName());
					deptDTO.setId(dept.getId());
					
					deptDTOs.add(deptDTO);
				}
				
				List<ItemDTO1> itemDTOs = new ArrayList<>();
				for(int j = 0 ; j < items.size() ; j++) {
					ItemDTO1 itemDTO = new ItemDTO1();
					
					itemDTO.setId(items.get(j).getId());
					itemDTO.setItemDsc(items.get(j).getitemDsc());
					
					itemDTOs.add(itemDTO);
				}
				
				groupDTO.setDept(deptDTOs);
				groupDTO.setItem(itemDTOs);
				System.out.println(itemDTOs);
				groupDTOs.add(groupDTO);
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return groupDTOs;
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() {
		List<DepartmentDTO> deptDTOs = new ArrayList<>();
		try {
			 List<Department> depts = deptDao.findAll();
			 depts.forEach(dept->{
				 DepartmentDTO deptDTO = new DepartmentDTO();
				 deptDTO.setDept(dept.getdepartmentName());
				 deptDTO.setId(dept.getId());
				 deptDTOs.add(deptDTO);
			 });
		}catch(Exception e) {
			e.printStackTrace();
		}
		return deptDTOs;
	}

}
