package com.fsoft.ez.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.entity.Process;
import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN10102;
import com.fsoft.ez.model.request.EZN102001Resquest;
import com.fsoft.ez.repository.EZN10102Repository;
import com.fsoft.ez.repository.NotificationRepository;
import com.fsoft.ez.repository.ProcessRepository;
import com.fsoft.ez.repository.TblDepartmentRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.service.EZN102Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EZN102ServiceImpl implements EZN102Service {

	@Autowired
	private TblDepartmentRepository tblDepartmentRepository;

	@Autowired
	private TblEmployeeRepository tblEmployeeRepository;

	@Autowired
	private ProcessRepository processRepository;

	@Autowired
	private EZN10102Repository ezn10102Repository;

	@Autowired
	private NotificationRepository notificationRepository;

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
	 * Create process
	 *
	 * @param EZN10201Request
	 *
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void createProcess(EZN102001Resquest ezn102001Resquest) throws Exception {
		// get current user login
		String currentEmplId = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmplid();
		int countDeptId = this.ezn10102Repository.countDepartment(ezn102001Resquest.getDepartmentId());

		if (countDeptId == 0) {

			Process createProcess = new Process();
			createProcess.setDepartmentId(ezn102001Resquest.getDepartmentId());
			createProcess.setProcessName(ezn102001Resquest.getProcessName());
			createProcess.setStatus(ezn102001Resquest.isStatus());
			createProcess.setCreateDate(LocalDateTime.now());
			createProcess.setDeleteFlag(false);
			createProcess.setCreateUser(currentEmplId);
			if (ezn102001Resquest.isOption()) {

				createProcess.setApproverId(ezn102001Resquest.getApproverId());
			} else {

				createProcess.setJobIndicator(ezn102001Resquest.getJobIndicator());
			}

			this.processRepository.save(createProcess);

			// save to notification
			Notification notification = new Notification();
			notification.setTypeNotifi(Constants.NEWS_APPROVE);
			notification.setProcessId(this.processRepository.save(createProcess).getProcessId());

			System.out.println("=====================" + this.processRepository.save(createProcess).getProcessId());

			String json = null;
			if (ezn102001Resquest.getApproverId() != null) {

				ObjectMapper objectMapper = new ObjectMapper();
				try {
					json = objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(ezn102001Resquest.getApproverId());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				String job = ezn102001Resquest.getJobIndicator();
			}
			this.notificationRepository.save(notification);

			System.out.println(notification.toString());

		} else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.MSG_004);
		}

	}
}
