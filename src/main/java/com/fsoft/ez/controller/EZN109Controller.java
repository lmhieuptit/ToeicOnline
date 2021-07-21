package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.model.request.EZN109001Request;
import com.fsoft.ez.service.EZN109Service;

@RestController
@RequestMapping("/api")
public class EZN109Controller {

	@Autowired
	private EZN109Service ezn109Service;

	@PutMapping(path = "/reject-news")
	public void reject(@RequestBody EZN109001Request ezn109001Request) {
		this.ezn109Service.rejectNews(ezn109001Request);
	}
}
