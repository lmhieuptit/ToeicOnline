package com.fsoft.ez.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.dto.NewsN00DTO;
import com.fsoft.ez.dto.NewsN01DTO;
import com.fsoft.ez.dto.NewsN02DTO;
import com.fsoft.ez.dto.NewsN03DTO;
import com.fsoft.ez.dto.NewsN04DTO;
import com.fsoft.ez.dto.NewsN05DTO;
import com.fsoft.ez.dto.NewsN06DTO;
import com.fsoft.ez.dto.NewsN09DTO;
import com.fsoft.ez.dto.NewsN10DTO;
import com.fsoft.ez.entity.Comment;
import com.fsoft.ez.entity.VoteOption;
import com.fsoft.ez.entity.custom.CommentCustom00;
import com.fsoft.ez.entity.custom.EZN203N03;
import com.fsoft.ez.entity.custom.HashtagCustom00;
import com.fsoft.ez.entity.custom.NewsCustom00;
import com.fsoft.ez.entity.custom.ReactionCustom00;
import com.fsoft.ez.entity.custom.ReactionCustom01;
import com.fsoft.ez.entity.custom.UserCustom01;
import com.fsoft.ez.model.response.EZN203N02Response;
import com.fsoft.ez.model.response.EZN203N03Response;
import com.fsoft.ez.repository.CommentRepository;
import com.fsoft.ez.repository.EZN203N02Repository;
import com.fsoft.ez.repository.EZN203N03Repository;
import com.fsoft.ez.repository.HashtagRepository;
//import com.fsoft.ez.entity.custom.VoteCustom00;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.EZN203Service;

@SuppressWarnings("unchecked")
@Service
public class EZN203ServiceImpl implements EZN203Service {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(EZN203ServiceImpl.class);
    @Autowired
    private EntityManager entityManager;

    // @Autowired
    // private ReactionRepository reactionRepository;

    @Autowired
    private VoteOptionRepository voteOptionRespository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private CommentRepository commentRespository;

    @Autowired
    private EZN203N02Repository ezn203n02Repository;

    @Autowired
    private EZN203N03Repository ezn203n03Repository;

    @Override
    public List<NewsN00DTO> getGeneralNews(int limit, int offset) {
        try {
            return this.getNewsByLimmitOffset(limit, offset);

        } catch (Exception e) {
            LOGGER.error("EZN203ServiceImpl.getGeneralNews(): {0}", e);
        }
        return null;
    }

    // @Override
    // public EZN203N07DTO likeNews(EZN203N07DTO dto) {
    // Optional<Reaction> reactionEntityOpt = reactionRepository
    // .findByUserIdAndNewsIdAndCategory(dto.getUserId(),
    // dto.getNewsId(),
    // Constants.REACTION_LIKE_NEWS);
    // Reaction reactionEntity = null;
    // boolean likeFlg;
    // if(reactionEntityOpt.isPresent()) {
    // reactionEntity = reactionEntityOpt.get();
    // // delete flag = true: unlike
    // if(reactionEntity.getDeleteFlag()) {
    // reactionEntity.setDeleteFlag(false);
    // likeFlg = true;
    // } else {
    // reactionEntity.setDeleteFlag(true);
    // likeFlg = false;
    // }
    // }else {
    // reactionEntity = new Reaction();
    // reactionEntity.setNewsId(dto.getNewsId());
    // reactionEntity.setUserId(dto.getUserId());
    // reactionEntity.setCategory(Constants.REACTION_LIKE_NEWS);
    // // reaction master id = 1 : LIKE
    // reactionEntity.setReactionMasterId(1L);
    // reactionEntity.setDeleteFlag(false);
    // likeFlg = true;
    // }
    // Reaction entityRs = reactionRepository.save(reactionEntity);
    // EZN203N07DTO result = new EZN203N07DTO();
    // result.setNewsId(entityRs.getNewsId());
    // result.setUserId(entityRs.getUserId().toString());
    // result.setLikeFlg(likeFlg);
    // return result;
    // }
    //
    // @Override
    // public EZN203N08DTO likeComment(EZN203N08DTO dto) {
    // Optional<Reaction> reactionEntityOpt = reactionRepository
    // .findByUserIdAndCommentIdAndCategory(dto.getUserId(),
    // dto.getCommentId(),
    // Constants.REACTION_LIKE_COMMENT);
    // Reaction reactionEntity = null;
    // boolean likeFlg;
    // if(reactionEntityOpt.isPresent()) {
    // reactionEntity = reactionEntityOpt.get();
    // // delete flag = true: unlike
    // if(reactionEntity.getDeleteFlag()) {
    // reactionEntity.setDeleteFlag(false);
    // likeFlg = true;
    // } else {
    // reactionEntity.setDeleteFlag(true);
    // likeFlg = false;
    // }
    // }else {
    // reactionEntity = new Reaction();
    // reactionEntity.setUserId(dto.getUserId());
    // reactionEntity.setNewsId(dto.getNewsId());
    // reactionEntity.setCommentId(dto.getCommentId());
    // // reaction master id = 1 : LIKE
    // reactionEntity.setReactionMasterId(1L);
    // reactionEntity.setCategory(Constants.REACTION_LIKE_COMMENT);
    // reactionEntity.setDeleteFlag(false);
    // likeFlg = true;
    // }
    // Reaction entityRs = reactionRepository.save(reactionEntity);
    // EZN203N08DTO result = new EZN203N08DTO();
    // result.setCommentId(entityRs.getCommentId());
    // result.setUserId(entityRs.getUserId().toString());
    // result.setLikeFlg(likeFlg);
    // return result;
    // }

    @Override
    public boolean comment(NewsN09DTO dto) {
        // TODO if comment id request = null -> set -1 cho comment id
        Optional<Comment> commentEntityOpt = this.commentRespository
                .findByCommentIdAndDeleteFlag(dto.getCommentId(), false);
        Comment commentEntity;
        if (commentEntityOpt.isPresent()) {
            commentEntity = commentEntityOpt.get();
        } else {
            commentEntity = new Comment();
        }
        commentEntity.setParentCommentId(dto.getParentCommentId());
        commentEntity.setUserId(Long.parseLong(dto.getUserId()));
        commentEntity.setNewsId(dto.getNewsId());
        commentEntity.setContent(dto.getComment());
        commentEntity.setDeleteFlag(false);
        this.commentRespository.save(commentEntity);
        return true;
    }

    @Override
    public boolean vote(NewsN10DTO dto) {
        Optional<VoteOption> voteOption = this.voteOptionRespository
                .findById(dto.getVoteOptionId());
        VoteOption voteOptionEntity;
        if (!voteOption.isPresent()) {
            return false;
        }
        voteOptionEntity = voteOption.get();
        String voteUserId = voteOptionEntity.getVoteUserId();
        String newVoteUserId = voteUserId;
        if (!StringUtils.isEmpty(voteUserId)) {
            List<String> voteUserIds = Arrays.asList(voteUserId.split(","));
            if (voteUserIds.contains(dto.getUserId())) {
                newVoteUserId = voteUserIds.stream()
                        .filter(p -> (p != null) && !p.equals(dto.getUserId()))
                        .collect(Collectors.joining(","));
            } else {
                newVoteUserId = String.join(",", voteUserId, dto.getUserId());
            }
        } else {
            newVoteUserId = String.join(",", voteUserId, dto.getUserId());
        }
        voteOptionEntity.setVoteUserId(
                StringUtils.isEmpty(newVoteUserId) ? null : newVoteUserId);
        this.voteOptionRespository.save(voteOptionEntity);

        return true;
    }

    private List<NewsN00DTO> getNewsByLimmitOffset(int limmit, int offset) {

        // 1. get news
        List<NewsCustom00> newsCustomList = this.getNewsList(offset, limmit);
        if (CollectionUtils.isEmpty(newsCustomList)) {
            return Collections.emptyList();
        }

        List<Long> newsIdList = newsCustomList.stream()
                .map(NewsCustom00::getNewsId).collect(Collectors.toList());

        String newsIds = newsCustomList.stream()
                .map(m -> m.getNewsId().toString())
                .collect(Collectors.joining(","));

        // get news category

        // 2. count likes new
        List<ReactionCustom00> cntLikeNews = this.getTotalLikeNews(newsIds);

        // 3. get vote

        List<VoteOption> voteOptionList = this.voteOptionRespository
                .findByDeleteFlagAndNewsIdIn(false, newsIdList);
        Map<Long, List<VoteOption>> voteOptionListByNewsIdMap = voteOptionList
                .stream().collect(Collectors.groupingBy(VoteOption::getNewsId));

        // get avatar url by list vote user id
        String voteUserIds = voteOptionList.stream()
                .map(VoteOption::getVoteUserId).filter(f -> f != null)
                .collect(Collectors.joining(","));
        Set<String> voteUserIdSet = Stream.of(voteUserIds.split(","))
                .map(m -> m).collect(Collectors.toSet());
        List<UserCustom01> userAvatarInfoList = this
                .getUserAvatarInfoList(voteUserIdSet);

        // 4. get hashtag
        List<HashtagCustom00> hashtag = this.getHashtag(newsIds);

        // 5. get comments
        List<CommentCustom00> comment = this.getComment(newsIds);

        // 6. count like comment
        List<ReactionCustom01> likeCmtList = this.getTotalLikeCmt(newsIds);

        // 7. combine data and return result combine
        List<NewsN00DTO> newsDataList = new ArrayList<>();
        for (NewsCustom00 news : newsCustomList) {
            NewsN00DTO dto = NewsN00DTO.builder().newsId(news.getNewsId())
                    .title(news.getTitle()).newsContent(news.getContent())
                    .thumbnailUrl(news.getThumbnailUrl())
                    .createUser(news.getCreateUser())
                    .createDate(news.getCreateDate())
                    .totalViews(news.getTotalViews())
                    .categoryId(news.getCategoryId())
                    .categoryName(news.getCategoryName())
                    .groupId(news.getGroupId()).groupName(news.getGroupName())
                    .personalId(news.getPersonalId())
                    .commentList(new ArrayList<>()).build();

            // set hashtag
            this.setHashtagList(dto, hashtag);

            // set user liked new list
            this.setUserLikedNews(dto, cntLikeNews);

            // set comment
            this.setUserComment(dto, comment, likeCmtList);

            // set vote
            this.setUserVote(dto, news,
                    voteOptionListByNewsIdMap.get(dto.getNewsId()),
                    userAvatarInfoList);

            newsDataList.add(dto);
        }
        return newsDataList;
    }

    private List<UserCustom01> getUserAvatarInfoList(
            Set<String> voteUserIdSet) {
        String sql = this.getQueryString("ezn203GetUserInfoByIds");
        Query query = this.entityManager.createNativeQuery(sql.toString(),
                UserCustom01.class);
        query.setParameter("userIds", voteUserIdSet);
        return query.getResultList();
    }

    private void setUserVote(NewsN00DTO dto, NewsCustom00 news,
            List<VoteOption> voteOptionList,
            List<UserCustom01> userAvatarInfoList) {
        if (CollectionUtils.isEmpty(voteOptionList)) {
            return;
        }
        // group user avatar info list by use id
        Map<String, UserCustom01> userAvatarInfoByUserIdMap = userAvatarInfoList
                .stream()
                .collect(Collectors.toMap(UserCustom01::getUserId, k -> k));

        NewsN02DTO voteDto = new NewsN02DTO();
        voteDto.setQuestion(news.getQuestion());
        List<NewsN03DTO> optionList = new ArrayList<>();
        for (VoteOption entity : voteOptionList) {
            NewsN03DTO option = new NewsN03DTO();
            option.setOptionId(entity.getVoteOptionId());
            option.setOption(entity.getOptionAnswer());
            List<String> userIds = !StringUtils.isEmpty(entity.getVoteUserId())
                    ? Arrays.asList(entity.getVoteUserId().split(","))
                    : Collections.emptyList();

            List<NewsN04DTO> userVotedList = new ArrayList<>();
            for (String userId : userIds) {
                UserCustom01 userEntity = userAvatarInfoByUserIdMap.get(userId);
                userVotedList.add(new NewsN04DTO(userId,
                        userEntity.getAccount(), userEntity.getAvatarUrl()));
            }
            option.setUserVotedList(userVotedList);
            optionList.add(option);
        }
        voteDto.setOptionList(optionList);
        voteDto.setEndDate(news.getVoteEndDate());
        dto.setVote(voteDto);
    }

    private NewsN06DTO toEZN203N06DTO(ReactionCustom01 entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, NewsN06DTO.class);
    }

    private void setUserComment(NewsN00DTO dto, List<CommentCustom00> comment,
            List<ReactionCustom01> likeCmtList) {
        List<CommentCustom00> commentByNewsId = comment.stream()
                .filter(f -> (f.getNewsId() != null)
                        && (f.getNewsId().compareTo(dto.getNewsId()) == 0))
                .collect(Collectors.toList());
        Map<Long, NewsN01DTO> commentMap = new HashMap<>();
        Map<Long, List<NewsN06DTO>> userLikeCmtByCmtIdMap = likeCmtList.stream()
                .map(this::toEZN203N06DTO)
                .collect(Collectors.groupingBy(NewsN06DTO::getCommentId));

        for (CommentCustom00 entity : commentByNewsId) {
            NewsN01DTO cmtdto = NewsN01DTO.builder()
                    .commentId(entity.getComment_id())
                    .parentCommentId(entity.getParentCommentId()).avatarURL("")
                    .comment(entity.getContent())
                    .createDate(entity.getCreateDate())
                    .CommentList(new TreeSet<>())
                    .userId(String.valueOf(entity.getUserId()))
                    .userName(entity.getAccount())
                    .reactionList(
                            userLikeCmtByCmtIdMap.get(entity.getComment_id()))
                    .build();

            commentMap.put(cmtdto.getCommentId(), cmtdto);
        }

        for (Entry<Long, NewsN01DTO> entry : commentMap.entrySet()) {
            NewsN01DTO cmtDto = entry.getValue();
            if (cmtDto.getParentCommentId() != null) {
                commentMap.get(cmtDto.getParentCommentId()).getCommentList()
                        .add(cmtDto);
            } else {
                dto.getCommentList().add(cmtDto);
            }
        }
    }

    private void setUserLikedNews(NewsN00DTO dto,
            List<ReactionCustom00> cntLikeNews) {
        if (CollectionUtils.isEmpty(cntLikeNews)) {
            return;
        }

        List<ReactionCustom00> cntLikeNewsByNewsId = cntLikeNews.stream()
                .filter(f -> (f.getNewsId() != null)
                        && (f.getNewsId().compareTo(dto.getNewsId()) == 0))
                .collect(Collectors.toList());

        List<NewsN05DTO> userLikeList = new ArrayList<>();
        for (ReactionCustom00 entity : cntLikeNewsByNewsId) {
            userLikeList.add(new NewsN05DTO(entity.getReactionName(),
                    entity.getUserId()));
        }
        dto.setUserLikeList(userLikeList);
        dto.setTotalLike(userLikeList.size());
    }

    private void setHashtagList(NewsN00DTO dto, List<HashtagCustom00> hashtag) {
        List<String> hashtagList = hashtag.stream()
                .filter(f -> (f.getNewsId() != null)
                        && (f.getNewsId().compareTo(dto.getNewsId()) == 0))
                .map(HashtagCustom00::getHashtagName)
                .collect(Collectors.toList());
        dto.setHashtagList(hashtagList);
    }

    private List<ReactionCustom01> getTotalLikeCmt(String newsIds) {
        String sql = this.getQueryString("ezn203GetTotalLikeCmt");
        Query query = this.entityManager.createNativeQuery(sql,
                ReactionCustom01.class);
        query.setParameter("newsIds", newsIds);
        return query.getResultList();
    }

    private List<CommentCustom00> getComment(String newsIds) {
        String sql = this.getQueryString("ezn203GetComments");
        Query query = this.entityManager.createNativeQuery(sql,
                CommentCustom00.class);
        query.setParameter("newsIds", newsIds);
        return query.getResultList();
    }

    private List<HashtagCustom00> getHashtag(String newsIds) {
        String sql = this.getQueryString("ezn203GetHashtags");
        Query query = this.entityManager.createNativeQuery(sql,
                HashtagCustom00.class);
        query.setParameter("newsIds", newsIds);
        return query.getResultList();
    }

    private List<ReactionCustom00> getTotalLikeNews(String newsIds) {
        if (StringUtils.isEmpty(newsIds)) {
            return Collections.emptyList();
        }
        String sql = this.getQueryString("ezn203GetTotalLikeNews");
        Query query = this.entityManager.createNativeQuery(sql,
                ReactionCustom00.class);
        query.setParameter("newsIds", newsIds);
        return query.getResultList();
    }

    private List<NewsCustom00> getNewsList(int offset, int limit) {
        String sql = this.getQueryString("ezn203GetNews");
        Query query = this.entityManager.createNativeQuery(sql.toString(),
                NewsCustom00.class);
        query.setParameter("offset", offset);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    private String getQueryString(String queryName) {
        Session session = this.entityManager.unwrap(Session.class);
        return session.getNamedQuery(queryName).getQueryString();
    }

    @Override
    public List<EZN203N02Response> getNewNotifications() {
        List<EZN203N02Response> response = this.ezn203n02Repository
                .getNewNotifications();
        return response;
    }

    @Override
    public EZN203N03Response getNewsDetail(Long newsId) {
        EZN203N03 response = this.ezn203n03Repository.getNewsDetail(newsId);

        List<String> hashTag = this.hashtagRepository.getListHashTag(newsId);
        List<String> voteAnser = this.voteOptionRespository
                .getListVoteOption(newsId);

        EZN203N03Response newsDetail = new EZN203N03Response();
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "newsId khong ton tai");

        }
        newsDetail.setGroupName(response.getGroupName());
        newsDetail.setAuthorId(response.getAuthorId());
        newsDetail.setAccount(response.getAccount());
        newsDetail.setDescrshort(response.getDescrshort());
        newsDetail.setTitle(response.getTitle());
        newsDetail.setContent(response.getContent());
        newsDetail.setType(response.getType());
        newsDetail.setThumbnailUrl(response.getThumbnailUrl());
        newsDetail.setHashtagId(response.getHashtagId());
        newsDetail.setPersonalName(response.getPersonalName());
        newsDetail.setAttachment(response.getAttachment());
        newsDetail.setTotalViews(response.getTotalViews());
        newsDetail.setLikeId(response.getLikeCount());
        newsDetail.setHashtag_name(hashTag);
        newsDetail.setOption_answer(voteAnser);
        return newsDetail;

    }

}
