package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.custom.EZN210N01;

public interface EZN210N01Repository extends JpaRepository<EZN210N01, String> {

    @Query(nativeQuery = true, value = "SELECT distinct gm.user_id, e.account, d.descrshort "
            + " FROM group_member gm join tbl_employee e join tbl_department d ON gm.user_id = e.emplid "
            + " WHERE e.deptid = d.deptid AND gm.group_id =:groupId AND gm.role_group = 0 AND gm.delete_flag = false")
    List<EZN210N01> getuserList(@Param("groupId") Long groupId);

    @Query(nativeQuery = true, value = "SELECT distinct gm.user_id, e.account, d.descrshort "
            + " FROM group_member gm join tbl_employee e join tbl_department d ON gm.user_id = e.emplid "
            + " WHERE e.deptid = d.deptid AND gm.group_id =:groupId AND gm.role_group = 1 AND gm.delete_flag = false")
    List<EZN210N01> getadminList(@Param("groupId") Long groupId);
}
