package com.fsoft.ez.controller;

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
import com.fsoft.ez.model.request.EZN205N01Request;
import com.fsoft.ez.model.respone.ez103.EZN103NewsDetailRespone;
import com.fsoft.ez.model.response.EZN205N00Response;
import com.fsoft.ez.model.response.EZN205N02Response;
import com.fsoft.ez.service.EZN205Service;

@RestController
@RequestMapping("/api")
public class EZN205Controller {

	@Autowired
	EZN205Service ezn205Service;

	/**
	 * get all news of user
	 *
	 * @return List News
	 */
	@GetMapping("/get-all-news-user")
	public List<EZN205N00Response> getAllNewsOfUser(long authorId) {

		return this.ezn205Service.getAllNewsOfUser(authorId);

	}

	/**
	 * get content of new
	 *
	 * @param newsId
	 *
	 * @return content
	 */
	@GetMapping("/get-news-detail-user")
	public EZN103NewsDetailRespone getNewsDetail(long newsId) {
		EZN103NewsDetailRespone res = new EZN103NewsDetailRespone();
		res.setContent(this.ezn205Service.getNewsDetail(newsId));

		return res;
	}

	/**
	 * get information of user
	 *
	 * @return EZN205N02Response response
	 */
	@GetMapping("/get-User-information")
	public EZN205N02Response getUserInformation(@RequestParam String empid) {
		return this.ezn205Service.getUserInformation(empid);

	}

	@Modifying
	@PutMapping("/set-cover-image-person")
	public String setCoverImage(@Valid EZN205N01Request request) throws Exception {
		
//		this.ezn205Service.setCoverImage(request);
		
		return ezn205Service.setCoverImage(request);

	}
}
