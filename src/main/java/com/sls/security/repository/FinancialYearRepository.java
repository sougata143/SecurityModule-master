package com.sls.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.security.entity.FinancialYear;

public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {

}
