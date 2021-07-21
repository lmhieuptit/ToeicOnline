package com.fsoft.ez.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.model.request.EZN302N00Request;
import com.fsoft.ez.model.response.EZN302N00Response;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.NotificationRepository;
import com.fsoft.ez.repository.ProcessRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.service.EZN302Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EZN302ServiceImpl implements EZN302Service {

	private TblEmployeeRepository tblEmployeeRepository;

	private NewsRepository newsRepository;

	private ProcessRepository processRepository;

	private NotificationRepository notificationRepository;

	@Autowired
	public EZN302ServiceImpl(TblEmployeeRepository tblEmployeeRepository, NewsRepository newsRepository,
			NotificationRepository notificationRepository, ProcessRepository processRepository) {
		super();
		this.tblEmployeeRepository = tblEmployeeRepository;
		this.newsRepository = newsRepository;
		this.processRepository = processRepository;
		this.notificationRepository = notificationRepository;
	}

	/**
	 * users whose birthday is date arg
	 *
	 * @param date
	 * @return users
	 */
	@Override
	public List<EZN302N00Response> getUsersBirthdayByDate(LocalDate date) {
		return this.tblEmployeeRepository.findByBirthDay(date.getMonthValue(), date.getDayOfMonth());
	}

	/**
	 * users whose birthday is month arg
	 *
	 * @param month
	 * @param year
	 * @return users
	 */
	@Override
	public List<EZN302N00Response> getUsersBirthdayByMonth(LocalDate date) {
		return this.tblEmployeeRepository.findEmpBirthDayMonth(date.getMonthValue());
	}

	/**
	 * create news type happy birthday greetings
	 *
	 * @param response
	 * @param principal
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void happyBirthday(EZN302N00Request response, Principal principal) {

		TblEmployee author = (TblEmployee) ((Authentication) principal).getPrincipal();

		News news = new News();
		news.setTitle(StringUtils.EMPTY);

		Long userIdFrom = Long.valueOf(author.getEmplid());
		news.setAuthorId(userIdFrom);

		// get department id from userIdTo
		String departmentId = this.tblEmployeeRepository.getDeptIdByEmplId(response.getUserIdTo().toString());
		news.setDepartmentId(Long.valueOf(departmentId));

		// get process id from userIdTo
		Long processId = this.processRepository.findProcessByEmpId(response.getUserIdTo().toString())
				.orElseGet(() -> null);
		news.setProcessId(processId);

		news.setPersonalId(response.getUserIdTo());
		news.setStatus(Constants.NEWS_STATUS_APPROVED);
		news.setType(Constants.NEWS_TYPE_HAPPY_BIRTHDAY);
		news.setApproveDate(LocalDateTime.now());
		news.setContent(response.getBirthdayGreetings());
		news.setDraftFlag(false);
		news.setDeleteFlag(false);

		this.newsRepository.save(news);

		Notification notification = new Notification();
		notification.setTypeNotifi(Constants.HAPPYBIRTHDAY);
		notification.setNewsId(this.newsRepository.save(news).getNewsId());

		this.notificationRepository.save(notification);
	}

	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Saigon")
	@Override
	public List<String> notifyBirthday() {
		LocalDate now = LocalDate.now();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();

		List<String> employeeIdList = this.tblEmployeeRepository.getNotifyBirthday(month, day);

		String json = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeIdList);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		Notification notification = new Notification();

		notification.setToUser(json);
		notification.setTypeNotifi(Constants.HAPPYBIRTHDAY_TODAY);

		this.notificationRepository.save(notification);

		return employeeIdList;
	}

}
