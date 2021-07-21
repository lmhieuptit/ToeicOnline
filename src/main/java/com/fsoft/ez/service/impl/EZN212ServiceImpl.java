package com.fsoft.ez.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.ez.common.model.UserInfoDTO;
import com.fsoft.ez.common.utils.EZCommonUtils;
import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.custom.EZN212001;
import com.fsoft.ez.entity.custom.EZN212002;
import com.fsoft.ez.model.request.EZN212N01Request;
import com.fsoft.ez.repository.EZN212001Repository;
import com.fsoft.ez.repository.EZN212002Repository;
import com.fsoft.ez.repository.TblDepartmentRepository;
import com.fsoft.ez.service.EZN212Service;
import com.fsoft.ez.service.UserService;

@Service
@Transactional
public class EZN212ServiceImpl implements EZN212Service {

	private EZN212001Repository ezn212001Repository;

	private EZN212002Repository ezn212002Repository;

	private TblDepartmentRepository departmentRepository;
	
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	public EZN212ServiceImpl(EZN212001Repository ezn212001Repository, TblDepartmentRepository departmentRepository,
			EZN212002Repository ezn212002Repository, Environment env) {
		super();
		this.ezn212001Repository = ezn212001Repository;
		this.departmentRepository = departmentRepository;
		this.ezn212002Repository = ezn212002Repository;
		this.env = env;
	}

	/**
	 * get list news notification
	 *
	 * @return list news
	 */
	@Override
	public List<EZN212001> findNewsNotify(Long limit) {
		List<EZN212001> response = new ArrayList<>();
		if (limit == null) {
			response = this.ezn212001Repository.findNewsNotify();
		} else {
			response = this.ezn212001Repository.findNewsNotifyByLimit(limit);
		}
		return response;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void setCoverImage(EZN212N01Request request) throws IOException {

		String depit = request.getDeptId();

		if (!request.getCoverImage().isEmpty()) {
			
			String filePath = EZCommonUtils.saveImagesNews(request.getCoverImage(), env);
			
			TblDepartment department = this.departmentRepository.getDepartment(depit);

			department.setCoverImage(filePath);

			this.departmentRepository.save(department);
		}
	}

	@Override
	public EZN212002 getCompanyDetail() {
		UserInfoDTO user = this.userService.getUserInformation();
		return this.ezn212002Repository.getCompanyDetail(user.getCompany());
	}
}
