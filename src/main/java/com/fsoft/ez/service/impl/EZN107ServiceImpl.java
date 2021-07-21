package com.fsoft.ez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.ez.entity.custom.EZN107001;
import com.fsoft.ez.model.response.EZN107N00Response;
import com.fsoft.ez.repository.EZN107001Repository;
import com.fsoft.ez.repository.HashtagRepository;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.EZN107Service;

@Service
public class EZN107ServiceImpl implements EZN107Service {

    @Autowired
    private EZN107001Repository ezn107001Repository;
    
    @Autowired
    private VoteOptionRepository voteOptionRepository;
    
    @Autowired 
    private HashtagRepository hashtagRepository;

    @Override
    public EZN107N00Response getNewsById(Long newsId) {

    	EZN107001 news=this.ezn107001Repository.findNewsById(newsId);
		
		List<String> getListOption=this.voteOptionRepository.getListVoteOption(newsId);
		
		List<String> getListHagTag=this.hashtagRepository.getListHashTag(newsId);
		
		EZN107N00Response getNews=new EZN107N00Response();
		getNews.setTitle(news.getTitle());
		getNews.setContent(news.getContent());
		getNews.setFileContent(news.getAttachment());
		getNews.setQuestion(news.getQuestion());
		getNews.setAuthor(news.getAuthor());
		getNews.setType(news.getType());
		getNews.setCreateDate(news.getCreateDate());
		getNews.setDescr(news.getDesrc());
		getNews.setAccount(news.getAccount());
		getNews.setJobIndicator(news.getJobIndicator());
		getNews.setStatus(news.getStatus());
		getNews.setApproveDate(news.getApproveDate());
		getNews.setReasonReject(news.getReasonReject());
		getNews.setListVoteOption(getListOption);
		getNews.setListHagTag(getListHagTag);
		return getNews;
    }

}
