package com.sls.security.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.security.component.FinancialYearComponent;
import com.sls.security.dto.FinancialYearDTO;
import com.sls.security.entity.FinancialYear;
import com.sls.security.services.FinancialYearService;


@Service
public class FinancialYearServiceImpl implements FinancialYearService {
	
	@Autowired
	FinancialYearComponent finyearDao;

	@Override
	public List<FinancialYearDTO> getAllFinancialYear() {
		List<FinancialYearDTO> findtos = new ArrayList<>();
		try {
			List<FinancialYear> finyears = finyearDao.getAllFinancialYear();
			finyears.forEach(fin->{
				FinancialYearDTO findto = new FinancialYearDTO();
				findto.setFinYear(fin.getFinYear());
				findtos.add(findto);
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return findtos;
	}

}
