package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.model.response.EZN107N00Response;
import com.fsoft.ez.service.EZN107Service;

@RestController
@RequestMapping("/api")
public class EZN107Controller {

	@Autowired
	private EZN107Service ezn107Service;

	@GetMapping("/get-news-ez107")
	public EZN107N00Response getNews(@RequestParam("newsId") Long newsId) {
		
		return this.ezn107Service.getNewsById(newsId);
	}
}
