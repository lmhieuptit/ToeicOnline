package com.fsoft.ez.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Process;
import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN10101;
import com.fsoft.ez.entity.custom.EZN10102;
import com.fsoft.ez.model.request.EZN101001Request;
import com.fsoft.ez.repository.EZN101001Repository;
import com.fsoft.ez.repository.EZN10102Repository;
import com.fsoft.ez.repository.ProcessRepository;
import com.fsoft.ez.repository.TblDepartmentRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.service.EZN101Service;

@Service
public class EZN101ServiceImpl implements EZN101Service {

	@Autowired
	private TblDepartmentRepository tblDepartmentRepository;

	@Autowired
	private TblEmployeeRepository tblEmployeeRepository;

	@Autowired
	private EZN101001Repository ezn10101Responsitory;

	@Autowired
	private ProcessRepository processRepository;

	@Autowired
	private EZN10102Repository ezn10102Repository;
	
	/**
	 * Get list TblDepartment
	 *
	 * @return list department in database
	 */
	@Override
	public List<TblDepartment> getListDepartment() {
		return this.tblDepartmentRepository.findAll();
	}

	/**
	 * Get list TblEmployee
	 *
	 * @Param deptId
	 * @return list TblEmployee in database
	 */
	@Override
	public List<TblEmployee> findEmployeeByDeptId(String deptId) {

		return this.tblEmployeeRepository.findEmployeeByDeptId(deptId);
	}

	/**
	 * Get list job indicator
	 *
	 * @Param deptId
	 * @return list Position in database
	 */
	@Override
	public List<EZN10102> findJobIndicatorByDeptId(String deptId) {

		return this.ezn10102Repository.findJobIndecatorByDeptId(deptId);
	}

	/**
	 * Search process by name
	 *
	 * @param keySearchProcess
	 *
	 * @return listprocess
	 */
	@Override
	public List<EZN10101> getProcessByName(String keySearchProcess) {

		return this.ezn10101Responsitory.findProcessByName(keySearchProcess);
	}

	/**
	 * Edit process
	 *
	 * @param EZN101N00Request
	 *
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void editProcess(EZN101001Request ezn101001Request) throws Exception {

		Process editprocess = this.processRepository.findById(ezn101001Request.getProcessId()).get();
		editprocess.setProcessName(ezn101001Request.getProcessName());
		editprocess.setStatus(ezn101001Request.isStatus());
		editprocess.setDepartmentId(ezn101001Request.getDepartmentId());
		editprocess.setUpdateDate(LocalDateTime.now());

		if (ezn101001Request.isOption()) {

			editprocess.setApproverId(ezn101001Request.getApproverId());
			editprocess.setJobIndicator(null);
		} else {

			editprocess.setJobIndicator(ezn101001Request.getJobIndicator());
			editprocess.setApproverId(null);
		}

		this.processRepository.save(editprocess);
		
	}

	/**
	 * Delete Process
	 *
	 * @param EZN101N00Request
	 *
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public String deleteProcess(Long processId) throws Exception {

		int countNews = this.processRepository.countNewsByProcessId(processId);

		if (countNews == 0) {

			this.processRepository.deleteById(processId);
		}else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.MSG_003);
		}
		
		return Constants.SUCCESS_MSG;
	}

}
