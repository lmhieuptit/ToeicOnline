package com.fsoft.ez.controller;

import com.fsoft.ez.model.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.entity.custom.AccountInfo;
import com.fsoft.ez.service.UserRegistrationService;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@PostMapping("/user-registration")
	public void userRegistration(@RequestBody AccountInfo accountInfo) {
		this.userRegistrationService.userRegistration(accountInfo);
	}

	@PostMapping("/admin/create-user")
	public void userRegistration(@RequestBody CreateUserRequest requestDto) {
		this.userRegistrationService.createUser(requestDto);
	}
}
