package com.sls.security.component;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sls.security.entity.Department;
import com.sls.security.repository.DepartmentRepository;


@Component
public class DepartmentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> findAll() throws Exception {
    	return departmentRepository.findAll();
    }

    
    @Transactional
    public Department findById(long deptId){
    	return departmentRepository.findOne(deptId);
    }
    
    @Transactional
    public Department findByDeptName(String deptName) {
    	return departmentRepository.findByDepartmentName(deptName);
    }
    
}
