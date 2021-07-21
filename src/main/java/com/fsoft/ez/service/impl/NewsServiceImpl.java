package com.fsoft.ez.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import com.fsoft.ez.entity.custom.NewsFeedCustom02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.dao.NewsCommonDao;
import com.fsoft.ez.entity.Comment;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.NewsFeedCustom00;
import com.fsoft.ez.entity.custom.NewsFeedCustom01;
import com.fsoft.ez.entity.custom.TopicCategoryCustom00;
import com.fsoft.ez.model.request.CommentNewsRequest;
import com.fsoft.ez.model.request.LikeCommentRequest;
import com.fsoft.ez.model.request.LikeNewsRequest;
import com.fsoft.ez.model.request.NewsFeedUpViewN00Request;
import com.fsoft.ez.model.request.UpdateContentCommentRequest;
import com.fsoft.ez.model.request.VoteActionRequest;
import com.fsoft.ez.repository.CommentRepository;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.NewsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class NewsServiceImpl implements NewsService {

	private VoteOptionRepository voteOptionRespository;

	private CommentRepository commentRepository;

	private NewsRepository newsRepository;

	private NewsCommonDao newsCommonDao;

	@Autowired
	public NewsServiceImpl(CommentRepository commentRepository, VoteOptionRepository voteOptionRespository,
			NewsCommonDao newsCommonDao, NewsRepository newsRepository) {
		this.commentRepository = commentRepository;
		this.voteOptionRespository = voteOptionRespository;
		this.newsCommonDao = newsCommonDao;
		this.newsRepository = newsRepository;
	}

	@Override
	public List<NewsFeedCustom00> getAllNewsInfo(Long offset, Long limit) {
		StringBuilder orderByClauseBuilder = new StringBuilder("order by n.approve_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getFullInfoNews(orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}

	@Override
	public List<NewsFeedCustom01> getGeneralNewsByLimitOffset(String searchKey, List<Long> categoryIdList, Long offset, Long limit) {
		Boolean categoryIsEmpty = false;
		if(categoryIdList == null || categoryIdList.isEmpty()) categoryIsEmpty = true;
		TblEmployee tblEmployee = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String id = tblEmployee.getEmplid();
		String companyId = tblEmployee.getCompany();
		StringBuilder orderByClauseBuilder = new StringBuilder("order by n.approve_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getGeneralNews(Long.valueOf(id), companyId, searchKey+"%", searchKey+"%",categoryIsEmpty, categoryIdList, orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}

	@Override
	public List<NewsFeedCustom02> getNewsParentCommentDetailByLimitOffset(Long newsId, Long offset, Long limit) {
		StringBuilder orderByClauseBuilder = new StringBuilder("order by cmt.create_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getNewsParentCommentDetail(newsId, orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}

	@Override
	public List<NewsFeedCustom02> getNewsChildCommentDetailByLimitOffset(Long parentId, Long offset, Long limit) {
		StringBuilder orderByClauseBuilder = new StringBuilder("order by cmt.create_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getNewsChildCommentDetail(parentId, orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}

	@Override
	public List<NewsFeedCustom01> getGroupNewsByLimitOffset(Long groupId, String searchKey, List<Long> categoryIdList, Long offset, Long limit) {
		Boolean categoryIsEmpty = false;
		if(categoryIdList == null || categoryIdList.isEmpty()) categoryIsEmpty = true;
		StringBuilder orderByClauseBuilder = new StringBuilder("order by n.approve_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getGroupNews(groupId, searchKey+"%", searchKey+"%", categoryIsEmpty, categoryIdList, orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}

	@Override
	public List<NewsFeedCustom01> getPersonalNewsByLimitOffset(Long personId, String searchKey, List<Long> categoryIdList, Long offset, Long limit) {
		Boolean categoryIsEmpty = false;
		if(categoryIdList == null || categoryIdList.isEmpty()) categoryIsEmpty = true;
		StringBuilder orderByClauseBuilder = new StringBuilder("order by n.approve_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getPersonalNews(personId, searchKey+"%", searchKey+"%", categoryIsEmpty, categoryIdList, orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}
	
	@Override
	public List<NewsFeedCustom01> getCompanyNews(String searchKey, List<Long> categoryIdList, Long offset, Long limit) {
		Boolean categoryIsEmpty = false;
		if(categoryIdList == null || categoryIdList.isEmpty()) categoryIsEmpty = true;
		String companyId = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCompany();
		StringBuilder orderByClauseBuilder = new StringBuilder("order by n.approve_date desc");
		StringBuilder limitClauseBuilder = new StringBuilder("limit ").append(offset).append(", ").append(limit);
		return newsCommonDao.getCompanyNews(companyId, searchKey+"%", searchKey+"%",categoryIsEmpty, categoryIdList, orderByClauseBuilder.toString(), limitClauseBuilder.toString());
	}
	
	@Override
	public NewsFeedCustom01 getDetailNewsFeed(Long newsId) {
		return newsCommonDao.getDetailNewsFeed(newsId);
	}

	/**
	 * like or unlike a news
	 * 
	 * @param request
	 * @throws IOException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	@Override
	public void likeNews(LikeNewsRequest request) throws IOException {

		TblEmployee employee = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		Optional<String> usersLikedOptional = newsRepository.findUserslikeNewsActive(request.getNewsId());

		Set<String> userLikedSet = new HashSet<>();
		ObjectMapper mapper = new ObjectMapper();

		String usersLiked = usersLikedOptional.orElseGet(() -> null);

		try {

			userLikedSet = mapper.readValue(usersLiked, new TypeReference<Set<String>>() {
			});

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (Constants.REACTION_LIKE.equals(request.getLikeType())) {

			userLikedSet.add(employee.getEmplid());
			String newUserLikedJsonArray = mapper.writeValueAsString(userLikedSet);
			newsRepository.likeNewsActive(newUserLikedJsonArray, request.getNewsId());

		} else {

			userLikedSet.remove(employee.getEmplid());
			String newUserLikedJsonArray = mapper.writeValueAsString(userLikedSet);
			newsRepository.likeNewsActive(newUserLikedJsonArray, request.getNewsId());

		}
	}

	/**
	 * [VN] Commemt vào một bài viết hoặc reply một comment khác ở trong một bài
	 * viết
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	@Override
	public Comment commentNews(CommentNewsRequest request) {

		String userId = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmplid();

		Comment comment = new Comment();
		comment.setContent(request.getCommentContent());
		comment.setIsParentComment(true);

		// Nếu client có gửi lên id của comment được reply
		if (request.getParentCommentId() != null) {

			comment.setIsParentComment(false);
			comment.setParentCommentId(request.getParentCommentId());

		}

		comment.setNewsId(request.getNewsId());
		comment.setUserId(Long.valueOf(userId));
		comment.setDeleteFlag(false);
		return commentRepository.save(comment);
	}

	/**
	 * like or unlike a comment of a news
	 * 
	 * @param request
	 * @throws IOException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	@Override
	public void likeComment(LikeCommentRequest request) throws IOException {

		TblEmployee employee = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		Optional<String> userLikedOptional = commentRepository.findUserslikeCommentActive(request.getCommentId());

		Set<String> userLikedSet = new HashSet<>();
		ObjectMapper mapper = new ObjectMapper();

		String userLiked = userLikedOptional.orElseGet(() -> null);

		try {

			userLikedSet = mapper.readValue(userLiked, new TypeReference<Set<String>>() {
			});

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (Constants.REACTION_LIKE.equals(request.getLikeType())) {

			userLikedSet.add(employee.getEmplid());
			String newUserLikedJsonArray = mapper.writeValueAsString(userLikedSet);
			commentRepository.likeComment(newUserLikedJsonArray, request.getCommentId(), request.getNewsId());

		} else {

			userLikedSet.remove(employee.getEmplid());
			String newUserLikedJsonArray = mapper.writeValueAsString(userLikedSet);
			commentRepository.likeComment(newUserLikedJsonArray, request.getCommentId(), request.getNewsId());

		}
	}

	/**
	 * Update comment content
	 * 
	 * @param request
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	@Override
	public void updateContentComment(UpdateContentCommentRequest request) {
		commentRepository.updateContentComment(request.getNewCommentContent(), request.getCommentId());
	}

	/**
	 * Update commet set delete_flag = 1
	 * 
	 * @param id comment id
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteCommentLogic(id);
	}

	/**
	 * vote action: vote or unvote
	 * 
	 * @param request
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	@Override
	public void voteAction(VoteActionRequest request) throws IOException {

		TblEmployee employee = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		Optional<String> userVotedJsonArrOpt = voteOptionRespository.getUserVotedJsonArr(request.getVoteId());

		Set<String> userVotedSet = new HashSet<>();
		ObjectMapper mapper = new ObjectMapper();

		String userVotedJsonArr = userVotedJsonArrOpt.orElseGet(() -> null);

		try {

			userVotedSet = mapper.readValue(userVotedJsonArr, new TypeReference<Set<String>>() {
			});

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (Constants.ACTION_VOTE.equals(request.getVoteType())) {

			userVotedSet.add(employee.getEmplid());
			String newUserVotedJsonArr = mapper.writeValueAsString(userVotedSet);
			voteOptionRespository.voteActionForVoteOption(newUserVotedJsonArr, request.getVoteId());

		} else {

			userVotedSet.remove(employee.getEmplid());
			String newUserVotedJsonArr = mapper.writeValueAsString(userVotedSet);
			voteOptionRespository.voteActionForVoteOption(newUserVotedJsonArr, request.getVoteId());

		}
	}

	/**
	 * delete logic news (set deleteFlag = 1)
	 * 
	 * @param id
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void deleteNews(Long id) {
		newsRepository.deleteNewsLogic(id);
	}

	/**
	 * delete logic news in group (set deleteFlag = 1)
	 * 
	 * @param id
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void deleteNewsGroup(Long newsId, Long groupId) {
		newsRepository.deleteNewsGroupLogic(newsId, groupId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void increaseViews(NewsFeedUpViewN00Request request) {

		Optional<News> newsOpt = newsRepository.findNewsActiveById(request.getNewsId());
		newsOpt.ifPresent(news -> {
			Long totalViewsNow = news.getTotalViews() == null ? 0L : news.getTotalViews();
			news.setTotalViews(totalViewsNow + 1);
			newsRepository.save(news);
		});

	}

	@Override
	public List<String> getTopicHashtag() {
		return newsCommonDao.getTopicHashtag();
	}
}
