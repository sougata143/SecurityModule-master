package com.sls.security.services;

import java.util.List;

import com.sls.security.dto.GateStoreEntryRegDtlDTO;
import com.sls.security.dto.POHeaderDTO;
import com.sls.security.dto.POSupplierDTO;

public interface POHeaderService {
	
	List<POHeaderDTO> getAllPOHeader();
	POHeaderDTO getPOHeaderById(String id);
	void savePOHeader(POHeaderDTO pohdr);
	void updatePOHeader(POHeaderDTO pohdr);
	void deletePOHeader(String id);
	POSupplierDTO getPoSupplier(String id);
	POSupplierDTO getStorePoSupplier(String id);
	POSupplierDTO getJutePoSupplier(String id);
	List<POHeaderDTO> getPOHeaderBySuppCodeAndStatus(String suppCode, String status);
	List<POHeaderDTO> getPOHeaderBySuppNameAndStatus(String suppName, String status);
	POHeaderDTO getPoByPoHdr(String pohdr);
	
}
