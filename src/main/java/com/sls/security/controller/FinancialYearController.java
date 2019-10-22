package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.FinancialYearDTO;
import com.sls.security.services.serviceImpl.FinancialYearServiceImpl;

@RestController
@RequestMapping("/financialyear/")
@CrossOrigin(origins = USER_HOST_SERVER)
public class FinancialYearController {

	@Autowired
	FinancialYearServiceImpl finService;
	
	@GetMapping("getallfinancialyear")
	public List<FinancialYearDTO> getAllFinancialYear(){
		return finService.getAllFinancialYear();
	}
	
}
