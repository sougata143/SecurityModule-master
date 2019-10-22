package com.sls.security.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.GateStoreEntryRegDtl;
import com.sls.security.repository.GateStoreEntryDtlRepository;

@Component
public class GateStoreEntryDtlComponent {
	
	@Autowired
	GateStoreEntryDtlRepository storeentrydtlRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public long getSeq() {
		return storeentrydtlRepository.getNextSeriesId();
	}
	
	@Transactional
	public List<GateStoreEntryRegDtl> getAllGateStoreEntryRegDtl(){
		return storeentrydtlRepository.findAll();
	}
	
	@Transactional
	public GateStoreEntryRegDtl getGateStoreEntryRegDtlById(long id) {
		return storeentrydtlRepository.findOne(id);
	}
	
	@Transactional
	public GateStoreEntryRegDtl saveGateStoreEntryRegDtl(GateStoreEntryRegDtl storeentrydtlEntity) {
		return storeentrydtlRepository.save(storeentrydtlEntity);
	}
	
	@Transactional
	public void deleteGateStoreEntryRegDtl(long id) {
		storeentrydtlRepository.delete(id);
	}
	
	@Transactional
	public List<GateStoreEntryRegDtl> getEntryDtlByHdrId(long hdrId){
		return storeentrydtlRepository.findByHdrId(hdrId);
	}
	
	@Transactional
	public void storedProcForWithoutPO(Long inGateHdrId) {
		String hql = "BEGIN STORE_PO_PROC(:IN_GATE_HDR_ID); END;";
		entityManager.createNativeQuery(hql)
						.setParameter("IN_GATE_HDR_ID", inGateHdrId)
						.executeUpdate();
	}
	
	@Transactional
	public void storedProcForExtraPOLineItem(Long inGateHdrId, String inPoNum) {
		String hql = "BEGIN ADD_STORE_PO_LINE(:IN_GATE_HDR_ID, :IN_PO_NUM); END;";
		entityManager.createNativeQuery(hql)
						.setParameter("IN_GATE_HDR_ID", inGateHdrId)
						.setParameter("IN_PO_NUM", inPoNum)
						.executeUpdate();
	}

}
