package com.sls.security.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.FinishingDispatchEntryDtl;
import com.sls.security.repository.FinishDispatchEntryDtlRepository;

@Component
public class FinishDispatchEntryDtlComponent {
	
	@Autowired
	FinishDispatchEntryDtlRepository finishdtlRepository;
	
	@Transactional
	public List<FinishingDispatchEntryDtl> getAllFinishingDispatchEntryDtl(){
		return finishdtlRepository.findAll();
	}
	
	@Transactional
	public void saveFinishingDispatchEntryDtl(FinishingDispatchEntryDtl finishentrydtl) {
		finishdtlRepository.save(finishentrydtl);
	}
	
	@Transactional
	public List<FinishingDispatchEntryDtl> getFinishingDispatchEntryDtlByHdrId(long hdrId){
		return finishdtlRepository.findByHdrId(hdrId);
	}

}
