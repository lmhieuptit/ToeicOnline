package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.service.EZN108Service;

@RestController
@RequestMapping("/api")
public class EZN108Controller {

	@Autowired
	private EZN108Service ezn108Service;

	@PutMapping("/accept-news")
	public void acceptNews(@RequestParam("newsId") Long newsId) {
		this.ezn108Service.acceptNews(newsId);
	}
}
