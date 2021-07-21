package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.TblUserRoles;

public interface TblUserRolesRepository extends JpaRepository<TblUserRoles, Long> {

    @Query(nativeQuery = true, value = "select code_role from tbl_user_roles where email = :email")
    List<String> findByEmail(@Param("email") String email);

}
