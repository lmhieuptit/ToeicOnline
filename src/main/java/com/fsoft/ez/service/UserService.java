package com.fsoft.ez.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fsoft.ez.common.model.UserDTO;
import com.fsoft.ez.common.model.UserInfoDTO;
import com.fsoft.ez.entity.TblEmployee;

public interface UserService {

    UserDTO getUserInfo(String email, String tenantId);

    TblEmployee getUserInfo(String email);

    List<GrantedAuthority> getUserRole(String email);

    UserInfoDTO getUserInformation();
}
