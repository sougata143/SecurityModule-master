package com.sls.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sls.security.entity.FinishingDispatchEntryDtl;


@Repository
public interface FinishDispatchEntryDtlRepository extends JpaRepository<FinishingDispatchEntryDtl, Long> {

	List<FinishingDispatchEntryDtl> findByHdrId(long hdrId);
	
}
