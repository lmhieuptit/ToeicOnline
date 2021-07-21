package com.fsoft.ez.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.fsoft.ez.entity.Comment;
import com.fsoft.ez.entity.custom.NewsFeedCustom02;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.custom.NewsFeedCustom00;
import com.fsoft.ez.entity.custom.NewsFeedCustom01;
import com.fsoft.ez.entity.custom.TopicCategoryCustom00;
import com.fsoft.ez.model.request.CommentNewsRequest;
import com.fsoft.ez.model.request.LikeCommentRequest;
import com.fsoft.ez.model.request.LikeNewsRequest;
import com.fsoft.ez.model.request.NewsFeedUpViewN00Request;
import com.fsoft.ez.model.request.UpdateContentCommentRequest;
import com.fsoft.ez.model.request.VoteActionRequest;
import com.fsoft.ez.service.NewsService;

@RestController
@RequestMapping("/api")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	@GetMapping("/get-general-news")
	public List<NewsFeedCustom01> getGeneralNews(
			@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
			@RequestParam(name = "searchKey", required = false, defaultValue = StringUtils.EMPTY) String searchKey,
			@RequestParam(name = "categoryId", required = false) List<Long> categoryIdList
			) {
		if(categoryIdList == null) categoryIdList = Collections.emptyList();
		return this.newsService.getGeneralNewsByLimitOffset(searchKey, categoryIdList, offset, limit);
	}

	@GetMapping("/get-parent-comments")
	public List<NewsFeedCustom02> getNewsParentCommentDetailByLimitOffset(@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
																		  @RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
																		  @RequestParam(name = "news_id") Long newsId) {
		return this.newsService.getNewsParentCommentDetailByLimitOffset(newsId, offset, limit);
	}

	@GetMapping("/get-child-comments")
	public List<NewsFeedCustom02> getNewsChildCommentDetailByLimitOffset(@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
																		  @RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
																		  @RequestParam(name = "parent_id") Long parentId) {
		return this.newsService.getNewsChildCommentDetailByLimitOffset(parentId, offset, limit);
	}
	
	@GetMapping("/get-group-news")
	public List<NewsFeedCustom01> getGeneralNews(
			@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
			@RequestParam(name = "groupId") Long groupId,
			@RequestParam(name = "searchKey", required = false, defaultValue = StringUtils.EMPTY) String searchKey,
			@RequestParam(name = "categoryId", required = false) List<Long> categoryIdList) {
		if(categoryIdList == null) categoryIdList = Collections.emptyList();
		return this.newsService.getGroupNewsByLimitOffset(groupId, searchKey, categoryIdList, offset, limit);
	}
	
	@GetMapping("/get-detail-newsfeed")
	public NewsFeedCustom01 getDetaiNewsById(@RequestParam("newsId") Long newsId) {
		return this.newsService.getDetailNewsFeed(newsId);
	}
	
	@GetMapping("/get-person-news")
	public List<NewsFeedCustom01> getPersonalNews(
			@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
			@RequestParam(name = "personId") Long personId,
			@RequestParam(name = "searchKey", required = false, defaultValue = StringUtils.EMPTY) String searchKey,
			@RequestParam(name = "categoryId", required = false) List<Long> categoryIdList) {
		if(categoryIdList == null) categoryIdList = Collections.emptyList();
		return this.newsService.getPersonalNewsByLimitOffset(personId, searchKey, categoryIdList, offset, limit);
	}

//	@GetMapping("/get-general-news-info-by-limit-offset")
	public List<NewsFeedCustom00> getNewsData(
			@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Long limit) {
		return newsService.getAllNewsInfo(offset, limit);
	}
	
	@GetMapping("/get-company-news")
	public List<NewsFeedCustom01> getCompanyNews(
			@RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
			@RequestParam(name = "searchKey", required = false, defaultValue = StringUtils.EMPTY) String searchKey,
			@RequestParam(name = "categoryId", required = false) List<Long> categoryIdList){
		if(categoryIdList == null) categoryIdList = Collections.emptyList();
		return newsService.getCompanyNews(searchKey, categoryIdList, offset, limit);
	}
	
	@PostMapping("/like-news")
	public String likeNews(@Valid @RequestBody LikeNewsRequest request) throws IOException {
		newsService.likeNews(request);
		return Constants.SUCCESS_MSG;
	}
	
	@PostMapping("/comment-news")
	public Comment commentNews(@Valid @RequestBody CommentNewsRequest request) throws IOException {
		return newsService.commentNews(request);
	}
	
	@PostMapping("/like-comment")
	public String likeComment(@Valid @RequestBody LikeCommentRequest request) throws IOException{
		newsService.likeComment(request);
		return Constants.SUCCESS_MSG;
	}
	
	@PutMapping("/update-comment")
	public String updateComment(@Valid @RequestBody UpdateContentCommentRequest request) {
		newsService.updateContentComment(request);
		return Constants.SUCCESS_MSG;
	}
	
	@DeleteMapping("/delete-comment")
	public String deleteComment(@RequestParam("commentId") Long id) {
		newsService.deleteComment(id);
		return Constants.SUCCESS_MSG;
	}
	
	@PostMapping("vote-action")
	public String voteAction(@Valid @RequestBody VoteActionRequest request) throws IOException {
		newsService.voteAction(request);
		return Constants.SUCCESS_MSG;
	}
	
	@DeleteMapping("/delete-news")
	public String deleteNews(@RequestParam("newsId") Long id) {
		newsService.deleteNews(id);
		return Constants.SUCCESS_MSG;
	}
	
	@PutMapping("increase-views")
	public String upViews(@Valid @RequestBody NewsFeedUpViewN00Request request) {
		newsService.increaseViews(request);
		return Constants.SUCCESS_MSG;
	}
	
	@GetMapping("/get-topic-hashtag")
	public List<String> getTopicHashtag() {
		return newsService.getTopicHashtag();
	}
}
