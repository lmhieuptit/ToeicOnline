package com.fsoft.ez.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.model.request.EZN302N00Request;
import com.fsoft.ez.model.response.EZN302N00Response;
import com.fsoft.ez.service.EZN302Service;

@RestController
@RequestMapping("/api")
public class EZN302Controller {

	private EZN302Service ezn302Service;

	@Autowired
	public EZN302Controller(EZN302Service ezn302Service) {
		super();
		this.ezn302Service = ezn302Service;
	}

	@GetMapping("/users-birthday-today")
	public List<EZN302N00Response> getUsersBirthdayToday() {
		return this.ezn302Service.getUsersBirthdayByDate(LocalDate.now());
	}

	@GetMapping("/users-birthday-this-month")
	public List<EZN302N00Response> getUsersWithBirthdaysThisMonth() {
		LocalDate now = LocalDate.now();
		return this.ezn302Service.getUsersBirthdayByMonth(now);
	}

	@GetMapping("/users-birthday-next-month")
	public List<EZN302N00Response> getUsersWithBirthdaysNextMonth() {
		LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
		return this.ezn302Service.getUsersBirthdayByMonth(nextMonthDate);
	}

	@PostMapping("/happy-birthday")
	public String happyBirthday(@RequestBody EZN302N00Request request, Principal principal) {
		this.ezn302Service.happyBirthday(request, principal);
		return Constants.SUCCESS_MSG;
	}

	@GetMapping("/notify-Birthday")
	public List<String> notifyBirthday() {
		return this.ezn302Service.notifyBirthday();
	}
}
