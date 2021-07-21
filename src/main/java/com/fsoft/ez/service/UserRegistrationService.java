package com.fsoft.ez.service;

import com.fsoft.ez.model.request.CreateUserRequest;
import com.fsoft.ez.entity.custom.AccountInfo;

public interface UserRegistrationService {

	void userRegistration(AccountInfo accountInfo);

	void createUser(CreateUserRequest requestDto);
}
