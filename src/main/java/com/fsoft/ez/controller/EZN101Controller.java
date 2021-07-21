package com.fsoft.ez.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN10101;
import com.fsoft.ez.entity.custom.EZN10102;
import com.fsoft.ez.model.request.EZN101001Request;
import com.fsoft.ez.service.EZN101Service;

@RestController
@RequestMapping("/api/admin")
public class EZN101Controller {

	@Autowired
	private EZN101Service ezn101Service;

	@GetMapping("/all-department")
	public List<TblDepartment> getAllDepartment() {

		return this.ezn101Service.getListDepartment();
	}

	@GetMapping("/all-employee")
	public List<TblEmployee> findEmployeeByDeptId(@RequestParam("deptId") String deptId) {
		return this.ezn101Service.findEmployeeByDeptId(deptId);
	}

	@GetMapping("/all-jobindicator-department")
	public List<EZN10102> findPositionByDeptId(@RequestParam("deptId") String deptId) {
		return this.ezn101Service.findJobIndicatorByDeptId(deptId);
	}

	@GetMapping("/get-process-by-name")
	public List<EZN10101> getProcessByName(@RequestParam String keySearchProcess) {

		return this.ezn101Service.getProcessByName(keySearchProcess);
	}

	@PutMapping(path = "/edit-process")
	public void editProcess(@RequestBody @Valid EZN101001Request ezn101001Request) throws Exception {

		this.ezn101Service.editProcess(ezn101001Request);

	}

	@DeleteMapping(path = "/delete-process")
	public String deleteProcess(@RequestParam("processId") Long processId) throws Exception {

		return this.ezn101Service.deleteProcess(processId);
	}

}
