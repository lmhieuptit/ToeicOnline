package com.fsoft.ez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN106001;
import com.fsoft.ez.model.response.EZN106N00Response;
import com.fsoft.ez.repository.EZN106001Repository;
import com.fsoft.ez.repository.HashtagRepository;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.EZN106Service;
import com.fsoft.ez.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EZN106ServiceImpl implements EZN106Service {

    @Autowired
    private EZN106001Repository ezn106001Repository;
    
    @Autowired
    private VoteOptionRepository voteOptionRepository;
    
    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private UserService userService ;
    
    @Override
    public EZN106N00Response getNewsById(Long newsId) {
    	
    	String emplId = userService.getUserInformation().getEmplId();
    	
    	log.info("=============emplId======= : {}",emplId);
    	
    	EZN106001 news =this.ezn106001Repository.findNewsById(emplId,newsId);
    	
    	log.info("ezn106001Repository.findNewsById ======= : {}",news.toString());
    	
		System.out.println(news.getAccount());
		
		List<String> getListOption=this.voteOptionRepository.getListVoteOption(newsId);
		
		List<String> getListHashTag=this.hashtagRepository.getListHashTag(newsId);
		
		EZN106N00Response getNews=new EZN106N00Response();
		
		getNews.setTitle(news.getTitle());
		
		getNews.setContent(news.getContent());
		
		getNews.setFileContent(news.getAttachment());
		
		getNews.setQuestion(news.getQuestion());
		
		getNews.setAuthor(news.getAuthor());
		
		getNews.setDescr(news.getDesrc());
		
		getNews.setType(news.getType());
		
		getNews.setCreateDate(news.getCreateDate());
		
		getNews.setAccount(news.getAccount());
		
		getNews.setStatus(news.getStatus());
		
		getNews.setApproveDate(news.getApproveDate());
		
		getNews.setListVoteOption(getListOption);
		
		getNews.setListHagTag(getListHashTag);
		
		return getNews;
    }

}
