package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.custom.EZN210;

public interface EZN210N004Repository extends JpaRepository<EZN210, Long> {

    @Query(nativeQuery = true, value = " SELECT DISTINCT empl.emplid, empl.name_display, empl.job_indicator, empl.account, dept.descr "
            + " FROM tbl_employee empl INNER JOIN tbl_department dept ON empl.deptid = dept.deptid "
            + " WHERE empl.name_display like :keySearch% ")
    List<EZN210> getSuggestMembers(@Param("keySearch") String keySearch);

}
