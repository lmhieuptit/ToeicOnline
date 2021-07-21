package com.fsoft.ez.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN10102;
import com.fsoft.ez.model.request.EZN102001Resquest;
import com.fsoft.ez.service.EZN102Service;

@RestController
@RequestMapping("/api/admin")
public class EZN102Controller {

	@Autowired
	private EZN102Service ezn102Service;

	@GetMapping("/all-department-ez102")
	public List<TblDepartment> getAllDepartment() {

		return this.ezn102Service.getListDepartment();
	}

	@GetMapping("/all-employee-ez102")
	public List<TblEmployee> findEmployeeByDeptId(@RequestParam("deptId") String deptId) {

		return this.ezn102Service.findEmployeeByDeptId(deptId);
	}

	@GetMapping("/all-jobindicator-department-ez102")
	public List<EZN10102> findPositionByDeptId(@RequestParam("deptId") String deptId) {

		return this.ezn102Service.findJobIndicatorByDeptId(deptId);
	}

	@PostMapping("/create-process")
	public void createProcess(@RequestBody @Valid EZN102001Resquest ezn102001Resquest) throws Exception {

		this.ezn102Service.createProcess(ezn102001Resquest);

	}
}
