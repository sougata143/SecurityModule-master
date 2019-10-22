package com.sls.security.services;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sls.security.dto.FinishingDispatchRegHdrDTO;
import com.sls.security.dto.FinishingDispatchRegHdrDTOSave;

public interface FinishingDispatchRegHdrService {

	List<FinishingDispatchRegHdrDTO> getAllFinishingDispatchRegHdr();
	
	FinishingDispatchRegHdrDTO getFinishingDispatchRegHdrById(long id);
	
	ResponseEntity<FinishingDispatchRegHdrDTOSave> 
							saveFinishingDispatchRegHdr(FinishingDispatchRegHdrDTOSave finishingdispatch);
	
	ResponseEntity<FinishingDispatchRegHdrDTO> 
							updateFinishingDispatchRegHdr(FinishingDispatchRegHdrDTO finishingdispatch);
	
	void deleteFinishingDispatchRegHdr(long id);
	
	List<FinishingDispatchRegHdrDTO> getAllFinishingDispatchByDate(Date fromDate, Date toDate);
	
	FinishingDispatchRegHdrDTO getAllFinishingDispatchRegHdrByChallanNo(String challanNo);
	
	ResponseEntity<FinishingDispatchRegHdrDTO> 
							updateFinishingDispatchRegHdrOut(FinishingDispatchRegHdrDTO finishingdispatch);
	
}
