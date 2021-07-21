package com.fsoft.ez.service.impl;

import com.fsoft.ez.model.request.CreateUserRequest;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.TblUserRoles;
import com.fsoft.ez.entity.custom.AccountInfo;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.repository.TblUserRolesRepository;
import com.fsoft.ez.service.UserRegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserResgisterServiceImpl implements UserRegistrationService{

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private TblUserRolesRepository tblUserRolesRepository;
	
	@Autowired
	private TblEmployeeRepository tblEmployeeRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void userRegistration(AccountInfo accountInfo) {
		
		TblEmployee employee = new TblEmployee();
		employee.setEmplid(accountInfo.getEmplId());
		employee.setEmailAddr(accountInfo.getEmail());
		employee.setDeptid(accountInfo.getDeptId());
		employee.setAccount(accountInfo.getAccount());
		employee.setCompany(accountInfo.getCompany());
		employee.setNameDisplay(accountInfo.getNameDisplay());
		employee.setLowerEmailAddr(accountInfo.getLowerEmailAddr());
		this.tblEmployeeRepository.save(employee);
		
		TblUserRoles tblUserRoles = new TblUserRoles();
		tblUserRoles.setCodeRole(accountInfo.getRoleCode());
		tblUserRoles.setEmail(accountInfo.getEmail());
		tblUserRoles.setCompany(accountInfo.getCompany());
		this.tblUserRolesRepository.save(tblUserRoles);
	}

	@Override
	public void createUser(CreateUserRequest requestDto) {
		if(requestDto == null || CollectionUtils.isEmpty(requestDto.getRoles())
				|| !CollectionUtils.isEmpty(tblEmployeeRepository.findByLowerEmailAddr(requestDto.getEmailAddr()))) {
			return;
		}

		TblEmployee user = modelMapper.map(requestDto, TblEmployee.class);
		List<TblUserRoles> roles = new ArrayList<>();
		requestDto.getRoles().forEach(requestRole -> {
			TblUserRoles role = modelMapper.map(requestRole, TblUserRoles.class);
			role.setEmail(user.getEmailAddr());
			roles.add(role);
		});

		tblEmployeeRepository.save(user);
		tblUserRolesRepository.saveAll(roles);
	}


}
