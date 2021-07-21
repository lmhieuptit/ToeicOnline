package com.fsoft.ez.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.ez.dto.UserInfoDTO;
import com.fsoft.ez.entity.custom.EZN105001;
import com.fsoft.ez.model.response.EZN105N00Response;
import com.fsoft.ez.repository.EZN105001Repository;
import com.fsoft.ez.repository.HashtagRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.EZN105Service;
import com.fsoft.ez.service.UserService;

@Service
public class EZN105ServiceImpl implements EZN105Service {

	@Autowired
	private EZN105001Repository ezn105001Repository;

	@Autowired
	private VoteOptionRepository voteOptionRepository;

	@Autowired
	private TblEmployeeRepository tblEmployeeRepository;

	@Autowired
	private HashtagRepository hashtagRepository;

	@Override
	public EZN105N00Response getNewsById(Long newsId) {

		EZN105001 news = this.ezn105001Repository.findNewsById(newsId);

		List<String> getListOption = this.voteOptionRepository.getListVoteOption(newsId);

		List<String> getListHagTag = this.hashtagRepository.getListHashTag(newsId);
		EZN105N00Response getNews = new EZN105N00Response();
		getNews.setTitle(news.getTitle());
		getNews.setContent(news.getContent());
		getNews.setAuthor(news.getAuthor());
		getNews.setDescr(news.getDesrc());
		getNews.setType(news.getType());
		getNews.setFileContent(news.getAttachment());
		getNews.setCreateDate(news.getCreateDate());
		getNews.setAccount(news.getAccount());
		getNews.setJobIndicator(news.getJobIndicator());
		getNews.setStatus(news.getStatus());
		getNews.setApproveDate(news.getApproveDate());
		getNews.setQuestion(news.getQuestion());
		getNews.setReasonReject(news.getReasonReject());
		getNews.setListOption(getListOption);
		getNews.setListHashTag(getListHagTag);
		Optional<UserInfoDTO> approverInfo = tblEmployeeRepository.getUserInfoByEmplId(news.getApproverId());
		getNews.setApproverInfo(approverInfo);

		return getNews;
	}

}
