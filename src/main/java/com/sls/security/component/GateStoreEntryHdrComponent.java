package com.sls.security.component;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.dto.DistinctStoreChalanAndSuppDTO;
import com.sls.security.entity.GateStoreEntryRegHdr;
import com.sls.security.repository.GateStoreEntryHdrRepository;

@Component
public class GateStoreEntryHdrComponent {

	@Autowired
	GateStoreEntryHdrRepository storeentryhdrRepository;

	@Transactional
	public List<GateStoreEntryRegHdr> getAllGateStoreEntryRegHdr(){
		return storeentryhdrRepository.findAll();
	}

	@Transactional
	public GateStoreEntryRegHdr getGateStoreEntryRegHdrById(long id) {
		return storeentryhdrRepository.findOne(id);
	}

	@Transactional
	public GateStoreEntryRegHdr saveGateStoreEntryRegHdr(GateStoreEntryRegHdr storeentryhdr) {
		
		return storeentryhdrRepository.save(storeentryhdr);
	}

	@Transactional
	public void deleteGateStoreEntryRegHdr(long id) {
		storeentryhdrRepository.delete(id);
	}

	@Transactional
	public List<GateStoreEntryRegHdr> getGateStoreEntryRegHdrByDate(Date fromDate, Date toDate){
		return storeentryhdrRepository.findByInDateBetween(fromDate,toDate);
	}

	@Transactional
	public GateStoreEntryRegHdr getGateStoreEntryHdrByPoNoAndChallanNo(String poNo, long challanNo) {
		return storeentryhdrRepository.findByPoNoAndChallanNo(poNo, challanNo);
	}
	
	@Transactional
	public GateStoreEntryRegHdr getGateStoreEntryHdrByPoNoAndChallanNoAndSuppCode(String poNo, 
																				long challanNo, String suppCode) {
		return storeentryhdrRepository.findByPoNoAndChallanNoAndSuppCode(poNo, challanNo, suppCode);
	}

	@Transactional
	public GateStoreEntryRegHdr getGateStoreEntryRegHdrByPoNo(String poNo) {
		return storeentryhdrRepository.findByPoNo(poNo);
	}

	@Transactional
	public GateStoreEntryRegHdr getGateStoreEntryRegHdrByChallanNo(long challanNo) {
		return storeentryhdrRepository.findByChallanNo(challanNo);
	}

	@Transactional
	public List<GateStoreEntryRegHdr> getGateStoreEntryRegHdrsByChallanNo(long challanNo) {
		return storeentryhdrRepository.findsByChallanNo(challanNo);
	}

	@Transactional
	public List<GateStoreEntryRegHdr> getGateStoreEntryRegHdrByChallanNoAndSuppCode(String suppCode, long challanNo) {
		return storeentryhdrRepository.findBySuppCodeAndChallanNo(suppCode, challanNo);
	}

	@Transactional
	public List<GateStoreEntryRegHdr> getAllGateStoreEntryHdrByIdAndChallanNo(Long hdrId, Long challanNo){
		return storeentryhdrRepository.findByHdrIdAndChallanNo(hdrId, challanNo);
	}

	/*@Transactional
	public Long getseq() {
		return storeentryhdrRepository.getPresentSeq();
	}*/

	/*@Transactional
	public List<DistinctStoreChalanAndSuppDTO> getDistinctChalanAndSupp(){
		return storeentryhdrRepository.findChalanAndSuppByInDateBetween();
	}*/

	@Transactional
	public List<Long> getChalan(){
		return storeentryhdrRepository.findChalanByInDateBetween();
	}

	@Transactional
	public List<String> getSupp(){
		return storeentryhdrRepository.findSuppCodeByInDateBetween();
	}

}
