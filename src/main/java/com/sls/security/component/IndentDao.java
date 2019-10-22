package com.sls.security.component;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.IndentHeader;
import com.sls.security.repository.IndentHeaderRepository;

@Component
public class IndentDao {

	@Autowired
	IndentHeaderRepository indentRepository;
	
	@Transactional
	public IndentHeader getIndentById(String indentNo) {
		return indentRepository.findOne(indentNo);
	}
	
}
