package com.sls.security.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.FinancialYear;
import com.sls.security.repository.FinancialYearRepository;

@Component
public class FinancialYearComponent {
	
	@Autowired
	FinancialYearRepository finyearRepo;
	
	@Transactional
	public List<FinancialYear> getAllFinancialYear(){
		return finyearRepo.findAll();
	}

}
