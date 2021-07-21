package com.fsoft.ez.service.impl;

import java.io.IOException;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.ez.common.model.AttachmentDTO;
import com.fsoft.ez.common.utils.EZCommonUtils;
import com.fsoft.ez.common.utils.TimeUtils;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.dto.EZN201N01DTO;
import com.fsoft.ez.entity.Hashtag;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.NewsHashtag;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.VoteOption;
import com.fsoft.ez.model.request.EZN201N00Request;
import com.fsoft.ez.model.request.EZN201N01Request;
import com.fsoft.ez.model.request.EZN201N04Request;
import com.fsoft.ez.model.response.EZN201N00Response;
import com.fsoft.ez.model.response.EZN201N01Response;
import com.fsoft.ez.model.response.EZN201N02Response;
import com.fsoft.ez.model.response.EZN201N03Response;
import com.fsoft.ez.repository.CategoryRepository;
import com.fsoft.ez.repository.HashtagRepository;
import com.fsoft.ez.repository.NewsHashtagRepository;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.ProcessRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.EZN201Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EZN201ServiceImpl implements EZN201Service {

	private CategoryRepository categoryRepository;

	private HashtagRepository hashtagRepository;

	private NewsRepository newsRepository;

	private NewsHashtagRepository newsHashtagRepository;

	private VoteOptionRepository voteOptionRepository;

	private ProcessRepository processRepository;

	private TblEmployeeRepository tblEmployeeRepository;
	
	private Environment env;

	@Autowired
	public EZN201ServiceImpl(CategoryRepository categoryRepository, HashtagRepository hashtagRepository,
			NewsRepository newsRepository, NewsHashtagRepository newsHashtagRepository,
			VoteOptionRepository voteOptionRepository, ProcessRepository processRepository,
			TblEmployeeRepository tblEmployeeRepository, Environment env) {
		super();
		this.categoryRepository = categoryRepository;
		this.hashtagRepository = hashtagRepository;
		this.newsRepository = newsRepository;
		this.newsHashtagRepository = newsHashtagRepository;
		this.voteOptionRepository = voteOptionRepository;
		this.processRepository = processRepository;
		this.tblEmployeeRepository = tblEmployeeRepository;
		this.env = env;
	}

	/**
	 * get all categories
	 *
	 * @return all categories in database
	 */
	@Override
	public List<EZN201N00Response> getAllCategoryNewsList() {
		return this.categoryRepository.getAllCategoriesNameActive();
	}

	/**
	 * find hashtag by name in database
	 *
	 * @param keyword
	 * @return list hashtag which have element's name like keyword
	 */
	@Override
	public List<Hashtag> findHashtagByNameLike(String keyword) {
		return this.hashtagRepository.findByHashtagNameLike(keyword);
	}

	/**
	 * find hashtag by name in database
	 *
	 * @param keyword
	 * @return list hashtag which have element's name is keyword arg
	 */
	@Override
	public List<Hashtag> findHashtagByName(String hashtagName) {
		return this.hashtagRepository.findByHashtagNameActive(hashtagName);
	}

	/**
	 * create news
	 *
	 * @param requestDTO
	 * @throws IOException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void createNews(EZN201N00Request requestDTO, Principal principal) throws IOException {

		TblEmployee author = (TblEmployee) ((Authentication) principal).getPrincipal();

		ObjectMapper mapper = new ObjectMapper();

		// get userId from token
		String userId = author.getEmplid();

		Long personId = null;

		// get department by userId or person id
		String departmentId = tblEmployeeRepository.getDeptIdByEmplId(userId);

		// get processId from userId
		// [VN] Nếu không tìm thấy quy trình bài viết nào thì chuyển về cho người phê
		// duyệt là admin, đang hardcode là null
		Long processId = processRepository.findProcessByEmpId(userId).orElseGet(() -> null);

		if (requestDTO.getPersonalFlag() != null && requestDTO.getPersonalFlag() == 1) {

			personId = Long.valueOf(userId);

		}

		List<String> hashtagListTemp = new ArrayList<>();

		boolean draftFlag = false;
		if (requestDTO.getDraftFlag() != null && requestDTO.getDraftFlag() == 1) {

			draftFlag = true;

		}

		// process for hashtag
		if (requestDTO.getHashtagJsonArray() != null && !requestDTO.getHashtagJsonArray().isEmpty()) {

			String[] hashtagList = mapper.readValue(requestDTO.getHashtagJsonArray(), String[].class);

			if (hashtagList != null) {
				for (String hashtagName : hashtagList) {
					List<Hashtag> temp = this.findHashtagByName(hashtagName);
					if (temp == null || temp.isEmpty()) {
						Hashtag newHashtag = new Hashtag();
						newHashtag.setHashtagName(hashtagName);
						newHashtag.setDeleteFlag(false);
						hashtagRepository.save(newHashtag);
					}
					hashtagListTemp.add(hashtagName);
				}
			}
		}

		News news = new News();

		// process for thumbnail
		if (requestDTO.getThumbnail() != null) {
			news.setThumbnailUrl(EZCommonUtils.saveImagesNews(requestDTO.getThumbnail(), env));
			news.setThumbnailOriginalName(requestDTO.getThumbnail().getOriginalFilename());
		}

		// process for create news
		news.setTitle(requestDTO.getTitle());
		news.setContent(requestDTO.getContent());
		Integer type = 1;
		if (requestDTO.getNotificationFlag() != null && requestDTO.getNotificationFlag() == 1) {
			type = 0;
		}
		news.setType(type);
		news.setCategoryId(requestDTO.getCategoryId());
		news.setDraftFlag(draftFlag);
		news.setProcessId(processId);
		news.setQuestion(requestDTO.getVoteTitle());
		news.setQuestionEndDate(null);
		Long limitFlag = requestDTO.getLimitFlag();
		if (limitFlag != null && limitFlag == 1L && !StringUtils.isEmpty(requestDTO.getEndDate())) {
			news.setQuestionEndDate(TimeUtils.toLocalDate(requestDTO.getEndDate(), Constants.DATE_FORMAT_MM_DD_YYYY));
		}
		news.setNumberOfChoice(requestDTO.getNumberOfChoice());
		news.setStatus(Constants.NEWS_STATUS_WATTING);
		news.setAuthorId(Long.valueOf(userId));
		news.setPersonalId(personId);
		news.setDepartmentId(Long.valueOf(departmentId));
		news.setDeleteFlag(false);
		news.setTotalViews(0L);
		Long newsIdCreated = this.newsRepository.save(news).getNewsId();

		// process for file attachment (xlsx, pdf, doc, docx,...)
		Set<AttachmentDTO> attachmentList = new HashSet<>();
		if (requestDTO.getFiles() != null) {
			for (MultipartFile file : requestDTO.getFiles()) {
				String filePath = EZCommonUtils.saveAttachmentNews(file, env, newsIdCreated);
				attachmentList.add(new AttachmentDTO(file.getOriginalFilename(), filePath));
			}
		}
		news.setAttachment(mapper.writeValueAsString(attachmentList));
		
		this.newsRepository.save(news);

		// process for news-hashtag
		List<NewsHashtag> newsHashtagListForSave = new ArrayList<>();
		hashtagListTemp.forEach(hashtagContent -> {

			Hashtag hashtag = new Hashtag();
			List<Hashtag> tempList = this.findHashtagByName(hashtagContent);

			if (tempList != null && !tempList.isEmpty()) {
				hashtag = tempList.get(0);
			}

			NewsHashtag newsHashtag = new NewsHashtag();
			newsHashtag.setNewsId(newsIdCreated);
			newsHashtag.setHashtagId(hashtag.getHashtagId());
			newsHashtagListForSave.add(newsHashtag);

		});
		newsHashtagRepository.saveAll(newsHashtagListForSave);

		// process for question and answer
		if (requestDTO.getVoteTitle() != null && !requestDTO.getVoteTitle().isEmpty()) {

			String[] options = mapper.readValue(requestDTO.getAnswerJsonArray(), String[].class);

			if (options != null) {
				List<VoteOption> voteOptionEntities = new ArrayList<>();
				for (String voteContent : options) {
					VoteOption voteOption = new VoteOption();
					voteOption.setNewsId(newsIdCreated);
					voteOption.setOptionAnswer(voteContent);
					voteOption.setDeleteFlag(false);
					voteOptionEntities.add(voteOption);
				}
				voteOptionRepository.saveAll(voteOptionEntities);
			}
		}
	}

	/**
	 * get detail for edit news action
	 * 
	 * @param id of news which we want to edit
	 * @return detail of news which we want to edit
	 */
	@Override
	public EZN201N01Response getDataForEditNews(Long id) {

		// get news for edit
		Optional<News> newsForEdit = newsRepository.findNewsActiveById(id);

		// if news for edit is not null
		if (newsForEdit.isPresent()) {

			EZN201N01Response responseDTO = new EZN201N01Response();
			List<String> hashTagNameList = this.newsHashtagRepository
					.getHashtagNamesByNewsId(newsForEdit.get().getNewsId());
			responseDTO.setHashtagList(hashTagNameList);

			// if this news has question
			if (!StringUtils.isEmpty(newsForEdit.get().getQuestion())) {

				// get question using in this news
				EZN201N02Response vote = new EZN201N02Response();
				vote.setTitle(newsForEdit.get().getQuestion());
				vote.setEndDate(null);
				Long limitFlag = 0L;
				if (newsForEdit.get().getQuestionEndDate() != null) {
					limitFlag = 1L;
					vote.setEndDate(newsForEdit.get().getQuestionEndDate()
							.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_MM_DD_YYYY)));
				}
				vote.setLimitFlag(limitFlag);

				// get list vote options
				List<EZN201N03Response> voteOptionList = this.voteOptionRepository
						.getvoteOptionListActive(newsForEdit.get().getNewsId());

				vote.setVoteOptionList(voteOptionList);
				vote.setNumberOfChoice(
						newsForEdit.get().getNumberOfChoice() == null ? 0 : newsForEdit.get().getNumberOfChoice());
				responseDTO.setVote(vote);

			}

			responseDTO.setNewsId(newsForEdit.get().getNewsId());
			responseDTO.setTitle(newsForEdit.get().getTitle());
			responseDTO.setContent(newsForEdit.get().getContent());
			responseDTO.setCategoryId(newsForEdit.get().getCategoryId());
			responseDTO.setContent(newsForEdit.get().getContent());
			responseDTO.setThumbnailFileName(newsForEdit.get().getThumbnailOriginalName());
			responseDTO.setThumbnailFilePath(newsForEdit.get().getThumbnailUrl());
			responseDTO.setFileContentJsonArr(newsForEdit.get().getAttachment());

			int personalFlag = 0;
			if (newsForEdit.get().getPersonalId() != null) {
				personalFlag = 1;
			}
			responseDTO.setPersonalFlag(personalFlag);
			Integer type = newsForEdit.get().getType();
			responseDTO.setNotificationFlag(type == 1 ? 0 : 1);

			return responseDTO;

		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void updateNews(EZN201N01Request requestDTO, Principal principal) throws IOException {

		TblEmployee user = (TblEmployee) ((Authentication) principal).getPrincipal();

		Optional<News> newsOptional = newsRepository.findNewsActiveById(requestDTO.getNewsId());

		if (newsOptional.isPresent()) {

			ObjectMapper mapper = new ObjectMapper();

			boolean draftFlag = false;
			if (requestDTO.getDraftFlag() != null && requestDTO.getDraftFlag() == 1) {
				draftFlag = true;
			}

			if (!Long.valueOf(user.getEmplid()).equals(newsOptional.get().getAuthorId())) {
				log.error("User " + user.getAccount() + " Không có quyền sửa bài viết này");
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,
						"User " + user.getAccount() + " Không có quyền sửa bài viết này");
			}

			Long userId = newsOptional.get().getAuthorId();

			Long personId = null;

			// get department by userId or person id
			String departmentId = tblEmployeeRepository.getDeptIdByEmplId(userId.toString());

			// get processId from userId
			Long processId = processRepository.findProcessByEmpId(userId.toString()).orElseGet(() -> null);

			if (requestDTO.getPersonalFlag() != null && requestDTO.getPersonalFlag() == 1) {

				personId = userId;

			}

			// process for create/update hashtag
			if (requestDTO.getHashtagJsonArray() != null && !requestDTO.getHashtagJsonArray().isEmpty() && !"[]".equals(requestDTO.getHashtagJsonArray())) {

				List<String> updateHashtagNewsList = mapper.readValue(requestDTO.getHashtagJsonArray(),
						new TypeReference<List<String>>() {
						});
				List<String> notInHashtagList = new ArrayList<>();

				if (updateHashtagNewsList != null) {

					List<EZN201N01DTO> usedHashtagList = newsHashtagRepository
							.getListHashtagByNewsId(newsOptional.get().getNewsId());

					updateHashtagNewsList.forEach(ht -> {

						notInHashtagList.add(ht);
						if (ht.isEmpty()) {
							return;
						}

						boolean isExits = usedHashtagList.stream().anyMatch(s -> ht.equals(s.getHashtagName()));
						if (isExits) {
							return;
						}

						List<Hashtag> isExitsNewHashtagList = hashtagRepository.findByHashtagNameActive(ht);
						if (isExitsNewHashtagList == null || isExitsNewHashtagList.isEmpty()) {
							Hashtag hashtag = new Hashtag();
							hashtag.setHashtagName(ht);
							hashtag.setDeleteFlag(false);
							hashtag = hashtagRepository.save(hashtag);
							NewsHashtag newsHashtag = new NewsHashtag();
							newsHashtag.setNewsId(newsOptional.get().getNewsId());
							newsHashtag.setHashtagId(hashtag.getHashtagId());
							newsHashtagRepository.save(newsHashtag);
							return;
						}
						NewsHashtag newsHashtag = new NewsHashtag();
						newsHashtag.setNewsId(newsOptional.get().getNewsId());
						newsHashtag.setHashtagId(isExitsNewHashtagList.get(0).getHashtagId());
						newsHashtagRepository.save(newsHashtag);

					});

					List<NewsHashtag> removeNewsHashtagList = newsHashtagRepository
							.getNewsHashtagNotIn(notInHashtagList, newsOptional.get().getNewsId());
					if (removeNewsHashtagList != null) {
						newsHashtagRepository.deleteAll(removeNewsHashtagList);
					}
				}

			} else {

				// delete all hashtag in this news
				newsHashtagRepository.deleteByNewsId(newsOptional.get().getNewsId());

			}

			if(!StringUtils.isEmpty(requestDTO.getVoteTitle())) {
				newsOptional.get().setQuestion(requestDTO.getVoteTitle());
			}
			List<VoteOption> oldVoteOptionList = voteOptionRepository.findByNewsId(requestDTO.getNewsId());

			if (StringUtils.isEmpty(requestDTO.getAnswerJsonArray())) {
				if (oldVoteOptionList != null && !oldVoteOptionList.isEmpty()) {
					oldVoteOptionList.forEach(oldVoteOption -> oldVoteOption.setDeleteFlag(true));
					voteOptionRepository.saveAll(oldVoteOptionList);
				}
			} else {

				List<EZN201N04Request> newVoteOptionList = mapper.readValue(requestDTO.getAnswerJsonArray(),
						new TypeReference<List<EZN201N04Request>>() {
						});

				if (oldVoteOptionList != null && !oldVoteOptionList.isEmpty()) {
					List<VoteOption> voteOptUpdatedList = new ArrayList<>();
					for (VoteOption oldVoteOption : oldVoteOptionList) {
						EZN201N04Request filtered = newVoteOptionList.stream()
								.filter(v -> oldVoteOption.getVoteOptionId().equals(v.getVoteOptionId())).findFirst()
								.orElse(null);
						if (filtered != null) {
							oldVoteOption.setOptionAnswer(filtered.getVoteOptionContent());
							oldVoteOption.setDeleteFlag(false);
							voteOptUpdatedList.add(oldVoteOption);
							newVoteOptionList.remove(filtered);
						} else {
							oldVoteOption.setDeleteFlag(true);
						}
					}
					newVoteOptionList.forEach(newVoteOption -> {
						VoteOption option = new VoteOption();
						option.setNewsId(requestDTO.getNewsId());
						option.setOptionAnswer(newVoteOption.getVoteOptionContent());
						option.setDeleteFlag(false);
						voteOptUpdatedList.add(option);
					});
					voteOptionRepository.saveAll(voteOptUpdatedList);
				} else {
					List<VoteOption> voteOptionList = new ArrayList<>();
					newVoteOptionList.forEach(newVoteOption -> {
						VoteOption option = new VoteOption();
						option.setNewsId(requestDTO.getNewsId());
						option.setOptionAnswer(newVoteOption.getVoteOptionContent());
						option.setDeleteFlag(false);
						voteOptionList.add(option);
					});
					voteOptionRepository.saveAll(voteOptionList);
				}
			}

			// process for thumbnail
			if (requestDTO.getIsUpdatedThumbnail() == 1) {
				newsOptional.get().setThumbnailUrl(null);
			}
			if (requestDTO.getThumbnail() != null) {
				newsOptional.get().setThumbnailUrl(EZCommonUtils.saveImagesNews(requestDTO.getThumbnail(), env));
				newsOptional.get().setThumbnailOriginalName(requestDTO.getThumbnail().getOriginalFilename());
			}

			// process for file attachment (xlsx, pdf, doc, docx,...)
			Set<AttachmentDTO> attachmentList = mapper.readValue(newsOptional.get().getAttachment(),
					new TypeReference<Set<AttachmentDTO>>() {
					});

			if(StringUtils.isEmpty(requestDTO.getRemovedAttachment())) requestDTO.setRemovedAttachment("[]");
			Set<AttachmentDTO> removedAttachmentList = mapper.readValue(requestDTO.getRemovedAttachment(),
					new TypeReference<Set<AttachmentDTO>>() {
					});

			removedAttachmentList.forEach(attachmentList::remove);

			if (requestDTO.getFiles() != null) {
				for (MultipartFile file : requestDTO.getFiles()) {
					String filePath = EZCommonUtils.saveAttachmentNews(file, env, newsOptional.get().getNewsId());
					attachmentList.add(new AttachmentDTO(file.getOriginalFilename(), filePath));
				}
			}
			newsOptional.get().setAttachment(mapper.writeValueAsString(attachmentList));

			// process for update News
			newsOptional.get().setTitle(requestDTO.getTitle());
			newsOptional.get().setContent(requestDTO.getContent());
			newsOptional.get().setProcessId(processId);
			Integer type = 1;
			if (requestDTO.getNotificationFlag() != null && requestDTO.getNotificationFlag() == 1) {
				type = 0;
			}
			newsOptional.get().setType(type);
			newsOptional.get().setDepartmentId(Long.valueOf(departmentId));
			newsOptional.get().setCategoryId(requestDTO.getCategoryId());
			newsOptional.get().setPersonalId(personId);
			newsOptional.get().setNumberOfChoice(requestDTO.getNumberOfChoice());
			if (requestDTO.getLimitFlag() != null && requestDTO.getLimitFlag() == 1) {
				newsOptional.get().setQuestionEndDate(
						TimeUtils.toLocalDate(requestDTO.getEndDate(), Constants.DATE_FORMAT_MM_DD_YYYY));
			} else {
				newsOptional.get().setQuestionEndDate(null);
			}
			newsOptional.get().setDraftFlag(draftFlag);
			newsOptional.get().setStatus(Constants.NEWS_STATUS_WATTING);
			newsOptional.get().setDeleteFlag(false);
			newsRepository.save(newsOptional.get());

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

}
