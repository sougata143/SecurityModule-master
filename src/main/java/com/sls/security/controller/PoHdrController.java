package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.component.ItemMasterDao;
import com.sls.security.dto.GateStoreEntryRegHdrDTO;
import com.sls.security.dto.POHeaderDTO;
import com.sls.security.dto.POSupplierDTO;
import com.sls.security.entity.ItemMaster;
import com.sls.security.services.serviceImpl.POHeaderServiceImpl;

@RestController
@RequestMapping("poheader")
public class PoHdrController {
	
	@Autowired
	POHeaderServiceImpl pohdrService;
	
	@GetMapping("/getAllPoHdr")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public List<POHeaderDTO> getAllPoHdr(){
		return pohdrService.getAllPOHeader();
	}
	
	@GetMapping("/getPoHdrById/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public POHeaderDTO getPoHdrById(@PathVariable("id") String id) {
		return pohdrService.getPOHeaderById(id);
	}
	
	@GetMapping("/getPoSupplierBbyPoNum/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public ResponseEntity<POSupplierDTO> getPOSupplier(@PathVariable("id") String id) {
		POSupplierDTO posupp = pohdrService.getPoSupplier(id);
		if(posupp!=null) {
			return new ResponseEntity<POSupplierDTO>(posupp, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<POSupplierDTO>(posupp, HttpStatus.NO_CONTENT);
		}
//		return pohdrService.getPoSupplier(id);
	}
	
	@GetMapping("/getapprovedpobysuppcode/{suppCode}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public List<POHeaderDTO> getPOBySuppCode(@PathVariable("suppCode") String suppCode){
		return pohdrService.getPOHeaderBySuppCodeAndStatus(suppCode, "3");
	}
	
	@GetMapping("/getapprovedpobysuppname/{suppName}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public List<POHeaderDTO> getPOBySuppName(@PathVariable("suppName") String suppName){
		return pohdrService.getPOHeaderBySuppNameAndStatus(suppName, "3");
	}
	
	@GetMapping("/getJutePoSupplierByPoNum/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public ResponseEntity<POSupplierDTO> getJutePOSupplier(@PathVariable("id") String id) {
		POSupplierDTO posupp = pohdrService.getJutePoSupplier(id);
		if(posupp!=null) {
			return new ResponseEntity<POSupplierDTO>(posupp, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<POSupplierDTO>(posupp, HttpStatus.NO_CONTENT);
		}
//		return pohdrService.getPoSupplier(id);
	}
	
	@GetMapping("/getStorePoSupplierByPoNum/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public ResponseEntity<POSupplierDTO> getStorePOSupplier(@PathVariable("id") String id) {
		POSupplierDTO posupp = pohdrService.getStorePoSupplier(id);
		if(posupp!=null) {
			return new ResponseEntity<POSupplierDTO>(posupp, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<POSupplierDTO>(posupp, HttpStatus.NO_CONTENT);
		}
//		return pohdrService.getPoSupplier(id);
	}
	
	
}
