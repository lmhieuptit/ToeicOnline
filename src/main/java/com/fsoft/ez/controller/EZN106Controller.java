package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.entity.custom.EZN106001;
import com.fsoft.ez.model.response.EZN106N00Response;
import com.fsoft.ez.service.EZN106Service;

@RestController
@RequestMapping("/api")
public class EZN106Controller {

	@Autowired
	private EZN106Service ezn106Service;

	@GetMapping("/get-news-ez106")
	public EZN106N00Response getNews(@RequestParam("newsId") Long newsId) {
		return this.ezn106Service.getNewsById(newsId);
	}
}
