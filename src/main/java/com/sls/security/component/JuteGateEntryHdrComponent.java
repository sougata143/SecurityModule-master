package com.sls.security.component;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.JuteEntryHeader;
import com.sls.security.repository.JuteGateEntryHdrRepository;

@Component
public class JuteGateEntryHdrComponent {
	
	@Autowired
	JuteGateEntryHdrRepository jutegatehdrRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public List<JuteEntryHeader> getAllJuteEntryHeader(){
		return jutegatehdrRepository.findAll();
	}
	
	@Transactional
	public JuteEntryHeader getJuteEntryHeaderById(long id) {
		return jutegatehdrRepository.findById(id);
	}

	public void save(JuteEntryHeader juteentryhdrEntity) {
		
		jutegatehdrRepository.save(juteentryhdrEntity);
		
	}

	public void delete(long id) {
		jutegatehdrRepository.delete(id);
		
	}
	
	@Transactional
	public List<JuteEntryHeader> getAllJuteEntryHeaderByDate(Date startDate, Date toDate){
		return jutegatehdrRepository.findByInDateBetween(startDate, toDate);
	}
	
	@Transactional
	public JuteEntryHeader getJuteEntryHeaderByPoNo(String poNo) {
		return jutegatehdrRepository.findByPoNo(poNo);
	}
	
	@Transactional
	public List<JuteEntryHeader> getJuteEntryHeaderByChallanNo(long challanNo) {
		return jutegatehdrRepository.findByChalanNo(challanNo);
	}
	
	@Transactional
	public JuteEntryHeader getJuteEntryHeaderBySuppNameAndChallanNo(String suppName, long challanNo) {
		return jutegatehdrRepository.findByBrokerNameAndChalanNo(suppName, challanNo);
	}
	
	@Transactional
	public JuteEntryHeader getJuteEntryHeaderByPoNoAndChallanNo(String poNo, long challanNo) {
		return jutegatehdrRepository.findByPoNoAndChalanNo(poNo, challanNo);
	}
	
	@Transactional
	public JuteEntryHeader getJuteEntryHeaderBySuppCodeAndChallanNo(String suppCode, long challanNo) {
		return jutegatehdrRepository.findBySuppCodeAndChalanNo(suppCode, challanNo);
	}
	
	@Transactional
	public void storedProcForWithoutPO(Long inGateHdrId) {
		String hql = "BEGIN JUTE_PO_PROC(:IN_GATE_HDR_ID); END;";
		entityManager.createNativeQuery(hql)
						.setParameter("IN_GATE_HDR_ID", inGateHdrId)
						.executeUpdate();
	}
	
	@Transactional
	public void storedProcForExtraPOLineItem(Long inGateHdrId, String inPoNum) {
		String hql = "BEGIN ADD_JUTE_PO_LINE(:IN_GATE_HDR_ID, :IN_PO_NUM); END;";
		entityManager.createNativeQuery(hql)
						.setParameter("IN_GATE_HDR_ID", inGateHdrId)
						.setParameter("IN_PO_NUM", inPoNum)
						.executeUpdate();
	}

}
