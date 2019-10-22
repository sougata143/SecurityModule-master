package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.FinishingDispatchRegHdrDTO;
import com.sls.security.dto.FinishingDispatchRegHdrDTOSave;
import com.sls.security.services.serviceImpl.FinishingDispatchRegHdrServiceImpl;

@RestController
@RequestMapping("finishingdispatchreghdr/")
@CrossOrigin(origins = USER_HOST_SERVER)
public class FinishingDispatchRegHdrController {
	
	@Autowired
	FinishingDispatchRegHdrServiceImpl finishingService;
	
	@GetMapping("getAllFinishingDispatchByDate/{fromDate}/{toDate}")
	public List<FinishingDispatchRegHdrDTO> getFinishingDispatchByDate(@PathVariable("fromDate") Date fromDate,
																		@PathVariable("toDate") Date toDate){
		return finishingService.getAllFinishingDispatchByDate(fromDate, toDate);
	}
	
	@GetMapping("getallfinishingdispatchhdr")
	public List<FinishingDispatchRegHdrDTO> getAllFinishingDispatchRegHdr(){
		return finishingService.getAllFinishingDispatchRegHdr();
	}
	
	@GetMapping("getfinishingdispatchhdrbyid/{hdrId}")
	public FinishingDispatchRegHdrDTO getFinishingDispatchRegHdrById(@PathVariable("hdrId") long hdrId) {
		return finishingService.getFinishingDispatchRegHdrById(hdrId);
	}
	
	@PostMapping("savefinishingdispacthhdr")
	public ResponseEntity<FinishingDispatchRegHdrDTOSave> saveFinishingDispatchRegHdr(@RequestBody FinishingDispatchRegHdrDTOSave finishingdispatch) {
		return finishingService.saveFinishingDispatchRegHdr(finishingdispatch);
	}
	
	@PostMapping("updatefinishingdispatchhdr")
	public ResponseEntity<FinishingDispatchRegHdrDTO> updateFinishingDispatchRegHdr(@RequestBody FinishingDispatchRegHdrDTO finishingdispatch) {
		return finishingService.updateFinishingDispatchRegHdr(finishingdispatch);
	}
	
	@DeleteMapping("deletefinishingdiapatch/{hdrId}")
	public void deleteFinishingDispatchRegHdr(@PathVariable("hdrId") long hdrId) {
		finishingService.deleteFinishingDispatchRegHdr(hdrId);
	}
	
	@GetMapping("getallfinishingdispatchhdrbychallanno/{challanNo}")
	public FinishingDispatchRegHdrDTO getAllFinishingDispatchRegHdrByChallanNo(@PathVariable("challanNo") String challanNo){
		return finishingService.getAllFinishingDispatchRegHdrByChallanNo(challanNo);
	}
	
	@PostMapping("updatefinishingdispatchhdrout")
	public ResponseEntity<FinishingDispatchRegHdrDTO> updateFinishingDispatchRegHdrOut(@RequestBody FinishingDispatchRegHdrDTO finishingdispatch) {
		return finishingService.updateFinishingDispatchRegHdrOut(finishingdispatch);
	}

}
