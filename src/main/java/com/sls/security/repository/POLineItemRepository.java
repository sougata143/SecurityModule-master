package com.sls.security.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sls.security.entity.POLineItem;

public interface POLineItemRepository extends JpaRepository<POLineItem, Long> {

   List<POLineItem> findByPoId(String poId);
   List<POLineItem> findByIndentId(String string);
   List<POLineItem> findByPoIdAndType(String poId, String type);
   
   @Query("select l from POLineItem l where l.poId = :poId and l.type != 'J'")
   List<POLineItem> findStorePoLineitemByPoId(@Param("poId") String poId);
}
