package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.model.response.EZN105N00Response;
import com.fsoft.ez.service.EZN105Service;

@RestController
@RequestMapping("/api")
public class EZN105Controller {

	@Autowired
	private EZN105Service ezn105Service;
	
	@GetMapping("/get-news-ez105")
	public EZN105N00Response getNewsById(@RequestParam("newsId") Long newsId) {

		return this.ezn105Service.getNewsById(newsId);
	}
}
