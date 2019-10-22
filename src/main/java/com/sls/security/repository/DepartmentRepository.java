package com.sls.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.security.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findById(Long i);
    
//    List<Department> findByDepartmentName(String departmentName);
    
//    @Query("select d from Department d where d")
//    Department findByDepartmentNameAndOrganization(@Param("deptId") long deptId, @Param("orgId") long orgId);
    
    List<Department> findByDepartmentNameAndOrgId(String deptName, long orgId);
    
//    @Query("select d from Department d where d.departmentName = :departmentName")
//    List<Department> findByDeptName(@Param)

   // List<Activity> findByUserName(String userName);

    // custom query example and return a stream
   // @Query("select u from User u where u.userName = :userName")
   // Stream<Activity> findByUserNameReturnStream(@Param("userName") String userName);
    
    Department findByDepartmentName(String deptName);

}
