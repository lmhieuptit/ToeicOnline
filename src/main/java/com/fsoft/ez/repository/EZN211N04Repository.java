package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.model.response.EZN211N02Response;

@Repository
public interface EZN211N04Repository
        extends JpaRepository<EZN211N02Response, Long> {
    @Query(nativeQuery = true, value = "SELECT gr.user_id, e.account, d.descr,gr.role_group, e.avatar_url, d.descrshort, e.job_indicator "
            + "FROM group_member gr INNER JOIN tbl_employee e ON gr.user_id = e.emplid INNER JOIN tbl_department d  ON e.deptid = d.deptid  "
            + "WHERE gr.group_id =:groupId AND e.account LIKE :keySearch% AND gr.delete_flag = 0;")
    List<EZN211N02Response> getAllMembers(@Param("groupId") Long groupId,
            @Param("keySearch") String keySearch);

}
