package com.fsoft.ez.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.common.utils.GoogleUtils;
import com.fsoft.ez.common.utils.JwtTokenUtil;
import com.fsoft.ez.dto.GooglePojo;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.service.LoginGoogleService;
import com.fsoft.ez.service.UserService;

@Service
public class LoginGoogleServiceImpl implements LoginGoogleService {

	private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private GoogleUtils googleUtils;
    
    @Autowired
	public LoginGoogleServiceImpl(JwtTokenUtil jwtTokenUtil, UserService userService, GoogleUtils googleUtils) {
		super();
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
		this.googleUtils = googleUtils;
	}


	@Override
	public String authenticationGoogleAccount(String code) throws IOException {
		String accessToken = googleUtils.getToken(code);
		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		TblEmployee employee = userService.getUserInfo(googlePojo.getEmail());
		if(employee == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Người dùng này chưa được cấp quyền để đăng nhập");
		}
		return this.jwtTokenUtil.generateToken(employee.getLowerEmailAddr());
	}
	
}
