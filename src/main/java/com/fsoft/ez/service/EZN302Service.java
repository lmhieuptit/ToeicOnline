package com.fsoft.ez.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import com.fsoft.ez.model.request.EZN302N00Request;
import com.fsoft.ez.model.response.EZN302N00Response;

public interface EZN302Service {

	/**
	 * users whose birthday is date arg
	 *
	 * @param date
	 * @return users
	 */
	List<EZN302N00Response> getUsersBirthdayByDate(LocalDate date);

	/**
	 * users whose birthday is month arg
	 *
	 * @param month
	 * @param year
	 * @return users
	 */
	List<EZN302N00Response> getUsersBirthdayByMonth(LocalDate date);

	/**
	 * create news type happy birthday greetings
	 *
	 * @param response
	 * @param principal
	 */
	void happyBirthday(EZN302N00Request response, Principal principal);

	List<String> notifyBirthday();
}
