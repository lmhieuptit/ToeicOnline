package com.fsoft.ez.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.custom.EZN212001;
import com.fsoft.ez.entity.custom.EZN212002;
import com.fsoft.ez.model.request.EZN212N01Request;
import com.fsoft.ez.service.EZN212Service;

@RestController
@RequestMapping("/api")
public class EZN212Controller {

	@Autowired
	private EZN212Service ezn212Service;

	@GetMapping("/get-news-notification")
	public List<EZN212001> findNewsNoti(@RequestParam(required = false) Long limit) {

		return this.ezn212Service.findNewsNotify(limit);
	}

	@Modifying
	@PutMapping("/set-cover-image-company")
	public String setCoverImage(@Valid EZN212N01Request request) throws IOException {

		this.ezn212Service.setCoverImage(request);

		return Constants.SUCCESS_MSG;
	}

	@GetMapping("/get-company-detail")
	public EZN212002 getCompanyDetail() {

		return this.ezn212Service.getCompanyDetail();
	}
}
