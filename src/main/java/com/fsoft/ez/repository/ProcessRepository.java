package com.fsoft.ez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {

    @Query(value = "Select count(news_id) from news n where n.process_id =:processid", nativeQuery = true)
    int countNewsByProcessId(@Param("processid") Long processId);

    // lưu ý id của department và id của user trong các bảng tbl_employee,
    // tbl_department, news, process
    @Query("select p.processId from Process p join TblEmployee e on cast(p.departmentId as string) = e.deptid where e.emplid = :empId and p.deleteFlag = false")
    Optional<Long> findProcessByEmpId(@Param("empId") String empId);

    @Query(nativeQuery = true, value = "SELECT count(p.approver_id) FROM process p WHERE p.approver_id =:empid AND  delete_flag = false ")
    Long countAproveIdbyEmplId(@Param("empid") String empid);

    @Query(nativeQuery = true, value = "SELECT process_id FROM process p  where p.approver_id =:empid AND  p.delete_flag = false; ")
    Long getProcessId(@Param("empid") String empid);

    @Query(nativeQuery = true, value = "SELECT p.process_id FROM process p "
            + "where p.department_id =:deptId AND p.job_indicator =:jobIndecator AND p.delete_flag = false;")
    Long getProcessIdByJobAndDeptId(@Param("deptId") String deptId,
            @Param("jobIndecator") String jobIndecator);

    @Query(nativeQuery = true, value = "SELECT p.process_id FROM process p WHERE p.approver_id =:emplId")
    Long getProcessIdByEmplId(@Param("emplId") String emplId);

    @Query(nativeQuery = true, value = "SELECT p.status FROM process p WHERE p.delete_flag = false AND p.process_id =:ProcessId ")
    Boolean getStatusById(@Param("ProcessId") Long ProcessId);
}
