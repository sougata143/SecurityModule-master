package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.SelectionQualityMstDTO;
import com.sls.security.entity.SelectioinQuallityMst;
import com.sls.security.services.serviceImpl.SelectionQualityMstServiceImpl;

@RestController
@RequestMapping("selectionquality/")
@CrossOrigin(origins = USER_HOST_SERVER)
public class SelectionQualityMstController {
	
	@Autowired
	SelectionQualityMstServiceImpl qualityService;
	
	@GetMapping("getAllSelectionQuality")
	public List<SelectionQualityMstDTO> getAllSelectioinQuallityMst(){
		return qualityService.getAllSelectionQuality();
	}

}
