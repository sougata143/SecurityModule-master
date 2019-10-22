package com.sls.security.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.security.entity.IndentHeader;

public interface IndentHeaderRepository extends JpaRepository<IndentHeader, String> {

   List<IndentHeader> findByStatus(String status);
   List<IndentHeader> findByMukam(String mukam);
   
  
}
