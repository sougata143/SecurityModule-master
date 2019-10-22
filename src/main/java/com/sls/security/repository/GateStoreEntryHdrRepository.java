package com.sls.security.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sls.security.dto.DistinctStoreChalanAndSuppDTO;
import com.sls.security.entity.GateStoreEntryRegHdr;

@Repository
public interface GateStoreEntryHdrRepository extends JpaRepository<GateStoreEntryRegHdr, Long> {

	/*@Query("SELECT s FROM GateStoreEntryHdr s WHERE s.hdrId =:hdrId")
	GateStoreEntryHdrRepository findByHdrId(@Param("hdrId") long hdrId);*/

	List<GateStoreEntryRegHdr> findByInDateBetween(Date fromDate, Date toDate);

	//	@Query("select distinct g.challanNo, g.suppCode from GateStoreEntryRegHdr g")
	//	List<DistinctStoreChalanAndSuppDTO> findChalanAndSuppByInDateBetween();

	@Query("select g.challanNo from GateStoreEntryRegHdr g")
	List<Long> findChalanByInDateBetween();

	@Query("select g.suppCode from GateStoreEntryRegHdr g")
	List<String> findSuppCodeByInDateBetween();

	GateStoreEntryRegHdr findByPoNoAndChallanNo(String poNo, long challanNo);
	GateStoreEntryRegHdr findByPoNoAndChallanNoAndSuppCode(String poNo, long challanNo,String suppCode);
	GateStoreEntryRegHdr findByPoNo(String poNo);
	GateStoreEntryRegHdr findByChallanNo(long challanNo);
	List<GateStoreEntryRegHdr> findBySuppCodeAndChallanNo(String suppCode, long challanNo);
	List<GateStoreEntryRegHdr> findByHdrIdAndChallanNo(Long hdrId, Long challanNo);

	@Query("select g from GateStoreEntryRegHdr g where g.challanNo = :challanNo")
	List<GateStoreEntryRegHdr> findsByChallanNo(@Param("challanNo") long challanNo);

	/*@Query(value = "SELECT GATE_STORE_ENTRY_REG_HDR_SEQ.currval FROM dual", nativeQuery = true)
	Long getPresentSeq();*/
}
