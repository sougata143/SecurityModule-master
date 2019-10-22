package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.JutePriceDTO;
import com.sls.security.dto.JuteQualityPriceMasterDTO;
import com.sls.security.services.serviceImpl.JuteQualityPriceMasterServiceImpl;

@RestController
@RequestMapping("quality")
@CrossOrigin(origins = USER_HOST_SERVER)
public class JuteQaulityPriceMasterController {
	
	@Autowired
	JuteQualityPriceMasterServiceImpl qualityService;
	
	@GetMapping("/getAllQuality")
	public List<JuteQualityPriceMasterDTO> getAllJuteQualityPriceMaster(){
		return qualityService.getAllJuteQualityPriceMaster();
	}
	
	@GetMapping("/getAllJuteQuality")
	public List<JutePriceDTO> getAllJuteQuality(){
		return qualityService.getAllJuteQuality();
	}

}
