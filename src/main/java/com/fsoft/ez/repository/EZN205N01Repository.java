package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.model.response.EZN205N02Response;

public interface EZN205N01Repository
        extends JpaRepository<EZN205N02Response, String> {

    @Query(nativeQuery = true, value = "SELECT e.emplid, e.email_addr, d.descr, e.name_display, "
    		+ " e.account, e.job_indicator, e.phone_number, e.address, e.date_of_birth, e.avatar_url, e.cover_image "
            + " FROM tbl_employee e INNER JOIN tbl_department d ON e.deptid = d.deptid WHERE emplid =:emplId ")
    EZN205N02Response getUserInfor(@Param("emplId") String emplId);
}
