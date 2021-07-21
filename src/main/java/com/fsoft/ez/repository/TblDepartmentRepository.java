package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.TblDepartment;

@Repository
public interface TblDepartmentRepository
        extends JpaRepository<TblDepartment, Long> {

    @Query(value = "SELECT dept.descr FROM TblDepartment dept WHERE dept.deptid =:deptId ")
    String getDeptNameByDeptId(@Param("deptId") String deptId);

    @Query(value = "SELECT d FROM TblDepartment d WHERE d.deptid = :deptId ")
    TblDepartment getDepartment(@Param("deptId") String depit);

    @Query(value = "SELECT d.descrshort FROM TblDepartment d WHERE d.deptid =:deptid ")
    String getDescShortById(@Param("deptid") String deptid);

    // @Query(value = " SELECT d.fullPath FROM TblDepartment d WHERE d.deptid
    // =:departmentId ")
    // List<Long> getDeptNameById(@Param("departmentId") Long departmentId);
}
