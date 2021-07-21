package com.fsoft.ez.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.ez.common.utils.EZCommonUtils;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.model.request.EZN205N01Request;
import com.fsoft.ez.model.response.EZN205N00Response;
import com.fsoft.ez.model.response.EZN205N02Response;
import com.fsoft.ez.repository.EZN205N01Repository;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.service.EZN205Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EZN205ServiceImpl implements EZN205Service {

	private NewsRepository newsRepository;

	private TblEmployeeRepository tblEmployeeRepository;

	private EZN205N01Repository ezn205N01Repository;

	private Environment env;

	@Autowired
	public EZN205ServiceImpl(NewsRepository newsRepository, TblEmployeeRepository tblEmployeeRepository,
			EZN205N01Repository ezn205N01Repository, Environment env) {
		super();
		this.newsRepository = newsRepository;
		this.tblEmployeeRepository = tblEmployeeRepository;
		this.ezn205N01Repository = ezn205N01Repository;
		this.env = env;
	}

	/**
	 * get all news
	 *
	 * @return ezn103N01ResponseDTOList
	 *
	 */

	@Override
	public List<EZN205N00Response> getAllNewsOfUser(Long authorId) {

		List<EZN205N00Response> response = new ArrayList<EZN205N00Response>();
		List<News> newsList = this.newsRepository.findNewsByIdOfUser(authorId);

		newsList.forEach(news -> {
			EZN205N00Response ezn205N00Response = new EZN205N00Response();

			ezn205N00Response.setNewsId(news.getNewsId());
			ezn205N00Response.setType(news.getType());
			ezn205N00Response.setTitle(news.getTitle());
			ezn205N00Response.setStatus(news.getStatus());
			ezn205N00Response.setDraftFlag(news.getDraftFlag());
			ezn205N00Response.setCreateDate(news.getCreateDate());
			ezn205N00Response.setGroupId(news.getGroupId());
			ezn205N00Response.setPersonalId(news.getPersonalId());

			if (news.getGroupId() != null) {
				ezn205N00Response.setWall(Constants.GROUP);
			} else if (news.getPersonalId() != null) {
				ezn205N00Response.setWall(Constants.PERSIONAL);
			} else if (news.getGroupId() == null && news.getPersonalId() == null) {
				ezn205N00Response.setWall(Constants.COMPANY);
			} else {
				ezn205N00Response.setWall(Constants.NEWSFEED);
			}

			response.add(ezn205N00Response);
		});

		return response;
	}

	/**
	 * get content of news
	 *
	 * @param newsId
	 *
	 * @return content
	 *
	 */
	@Override
	public String getNewsDetail(Long newsId) {
		Optional<News> news = this.newsRepository.findById(newsId);

		return news.get().getContent();
	}

	/**
	 * get information of user
	 *
	 * @return EZN205N02Response userInfoDTO
	 *
	 */
	@Override
	public EZN205N02Response getUserInformation(String emplId) {

		return this.ezn205N01Repository.getUserInfor(emplId);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String setCoverImage(EZN205N01Request request) throws Exception {
		log.info("parram request : {}", request.toString());
		String filePath = null;
		if (!request.getCoverImage().isEmpty()) {

			filePath = EZCommonUtils.saveImagesNews(request.getCoverImage(), env);

			String id = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getEmplid();

			this.tblEmployeeRepository.updateCoverImageById(filePath, id);

		}
		return filePath;

	}

}
