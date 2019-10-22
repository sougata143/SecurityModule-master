package com.sls.security.component;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.entity.Indent;
import com.sls.security.repository.IndentLineItemRepository;

@Component
public class IndentLineItemDao {

	@Autowired
	IndentLineItemRepository indentlineRepository;
	
	@Transactional
	public Indent getIndentLineitemByIndentNoAndItemCode(String indentNO, String itemCode) {
		return indentlineRepository.findByIndentHeaderIdAndItemId(indentNO, itemCode);
	}
	
}
