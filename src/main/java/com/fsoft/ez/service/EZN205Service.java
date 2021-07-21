package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.model.request.EZN205N01Request;
import com.fsoft.ez.model.response.EZN205N00Response;
import com.fsoft.ez.model.response.EZN205N02Response;

public interface EZN205Service {

	/**
	 * [get all new of user
	 *
	 * @return List News
	 */
	List<EZN205N00Response> getAllNewsOfUser(Long authorId);

	/**
	 * get content
	 *
	 * @param newsId
	 *
	 * @return content of new
	 */
	String getNewsDetail(Long newsId);

	/**
	 * get information of user
	 *
	 * @return EZN205N02Response reponse
	 */
	EZN205N02Response getUserInformation(String empid);

	/**
	 * set cover image
	 *
	 * @param request
	 */
	public String setCoverImage(EZN205N01Request request) throws Exception;
}
