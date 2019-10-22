package com.sls.security.services;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.JuteEntryHeaderDTO;
import com.sls.security.dto.JuteEntryHeaderdDTO;

public interface JuteGateEntryHdrService {

	List<JuteEntryHeaderDTO> getAllJuteGateEntryHeader();
	ResponseEntity<JuteEntryHeaderDTO> saveJuteGateEntryHeader(JuteEntryHeaderDTO entryHdr);
	ResponseEntity<JuteEntryHeaderDTO> updateJuteGateEntryHeader(JuteEntryHeaderDTO entryHdr);
	DeleteDTO deleteJuteEntryHeader(long id);
	JuteEntryHeaderDTO getJuteGateEntryHeaderById(long id);
	JuteEntryHeaderdDTO getJuteGateEntryHeaderById2(long id);
	List<JuteEntryHeaderDTO> getAllJuteGateEntryHdrByDate(Date startDate, Date toDate);
	JuteEntryHeaderDTO getAllJuteEntryHeaderBySuppNameAndChallanNo(String suppName, long challanNo);
	ResponseEntity<JuteEntryHeaderDTO> updateJuteGateEntryHeaderOut(JuteEntryHeaderDTO entryHdr);
	
	long getChallanWeightByChalanNoAndSupp(String suppName, long chalanNo);
	long getChallanWeightByChalanNoAndSuppCode(String suppCode, long chalanNo);
	
}
