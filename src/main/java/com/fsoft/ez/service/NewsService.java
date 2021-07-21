package com.fsoft.ez.service;

import java.io.IOException;
import java.util.List;

import com.fsoft.ez.entity.Comment;
import com.fsoft.ez.entity.custom.NewsFeedCustom00;
import com.fsoft.ez.entity.custom.NewsFeedCustom01;
import com.fsoft.ez.entity.custom.NewsFeedCustom02;
import com.fsoft.ez.entity.custom.TopicCategoryCustom00;
import com.fsoft.ez.model.request.CommentNewsRequest;
import com.fsoft.ez.model.request.LikeCommentRequest;
import com.fsoft.ez.model.request.LikeNewsRequest;
import com.fsoft.ez.model.request.NewsFeedUpViewN00Request;
import com.fsoft.ez.model.request.UpdateContentCommentRequest;
import com.fsoft.ez.model.request.VoteActionRequest;

public interface NewsService {

	List<NewsFeedCustom00> getAllNewsInfo(Long offset, Long limit);

	List<NewsFeedCustom01> getGeneralNewsByLimitOffset(String searchKey, List<Long> categoryIdList, Long offset, Long limit);

	List<NewsFeedCustom02> getNewsParentCommentDetailByLimitOffset(Long newsId, Long offset, Long limit);

	List<NewsFeedCustom02> getNewsChildCommentDetailByLimitOffset(Long parentId, Long offset, Long limit);

	List<NewsFeedCustom01> getGroupNewsByLimitOffset(Long groupId, String searchKey, List<Long> categoryIdList, Long offset, Long limit);
	
	void likeNews(LikeNewsRequest request) throws IOException;

	Comment commentNews(CommentNewsRequest request) throws IOException;

	void deleteComment(Long id);

	void updateContentComment(UpdateContentCommentRequest request);

	void voteAction(VoteActionRequest request) throws IOException;

	List<NewsFeedCustom01> getPersonalNewsByLimitOffset(Long personId, String searchKey, List<Long> categoryIdList,
														Long offset, Long limit);

	/**
	 * like or unlike a comment of a news
	 * 
	 * @param request
	 * @throws IOException
	 */
	void likeComment(LikeCommentRequest request) throws IOException;

	/**
	 * delete logic news (set deleteFlag = 1)
	 * 
	 * @param id
	 */
	void deleteNews(Long id);

	/**
	 * delete logic news in group (set deleteFlag = 1)
	 * @param newsId
	 * @param groupId
	 */
	void deleteNewsGroup(Long newsId, Long groupId);
	
	void increaseViews(NewsFeedUpViewN00Request request);

	List<String> getTopicHashtag();
	
	/**
	 * get newsfeed of company
	 * 
	 * @param offset
	 * @param limit
	 * @return newsfeed of company
	 */
	List<NewsFeedCustom01> getCompanyNews(String searchKey, List<Long> categoryIdList, Long offset, Long limit);
	
	/**
	 * get news detail
	 * 
	 * @return detail of news by id
	 */
	NewsFeedCustom01 getDetailNewsFeed(Long newsId);
}
