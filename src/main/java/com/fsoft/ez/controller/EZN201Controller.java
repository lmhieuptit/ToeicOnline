package com.fsoft.ez.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Hashtag;
import com.fsoft.ez.model.request.EZN201N00Request;
import com.fsoft.ez.model.request.EZN201N01Request;
import com.fsoft.ez.model.response.EZN201N00Response;
import com.fsoft.ez.model.response.EZN201N01Response;
import com.fsoft.ez.service.EZN201Service;

@RestController
@RequestMapping("/api")
public class EZN201Controller {

	@Autowired
	private EZN201Service ezn201Service;
	
	@GetMapping("/all-categories")
	public List<EZN201N00Response> getAllCategories(Principal principal) {
		return ezn201Service.getAllCategoryNewsList();
	}

	@GetMapping("/find-hashtag")
	public List<Hashtag> findHashtagByName(@RequestParam("keyword") String keyword) {
		return ezn201Service.findHashtagByNameLike(keyword);
	}

	@PostMapping("/create-news")
	public String createNews(@Valid EZN201N00Request requestDTO, Principal principal) throws IOException, BindException {
		ezn201Service.createNews(requestDTO, principal);
		return Constants.SUCCESS_MSG;
	}

	@GetMapping("/get-detail-news")
	public EZN201N01Response getDetailNews(@RequestParam("newsId") Long newsId) {
		return ezn201Service.getDataForEditNews(newsId);
	}

	@PutMapping("/edit-news")
	public String editNews(@Valid EZN201N01Request requestDTO, Principal principal) throws IOException {
		ezn201Service.updateNews(requestDTO, principal);
		return Constants.SUCCESS_MSG;
	}
}
