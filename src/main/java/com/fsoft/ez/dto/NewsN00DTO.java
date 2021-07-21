package com.fsoft.ez.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsN00DTO {

    public NewsN00DTO() {
    }

    public NewsN00DTO(String createUser, Long newsId, Long categoryId, String categoryName,
        Long groupId,
        String groupName, Long personalId, List<String> hashtagList, String title,
        String newsContent,
        String thumbnailUrl, NewsN02DTO vote, Long totalViews, int type, List<
                        NewsN05DTO> userLikeList,
        int totalLike, List<NewsN01DTO> commentList, LocalDate createDate) {
        super();
        this.createUser = createUser;
        this.newsId = newsId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.personalId = personalId;
        this.hashtagList = hashtagList;
        this.title = title;
        this.newsContent = newsContent;
        this.thumbnailUrl = thumbnailUrl;
        this.vote = vote;
        this.totalViews = totalViews;
        this.type = type;
        this.userLikeList = userLikeList;
        this.totalLike = totalLike;
        this.commentList = commentList;
        this.createDate = createDate;
    }

    private String createUser;

    private Long newsId;

    private Long categoryId;

    private String categoryName;

    private Long groupId;

    private String groupName;

    private Long personalId;

    private List<String> hashtagList;

    private String title;

    private String newsContent;

    private String thumbnailUrl;

    private NewsN02DTO vote;

    private Long totalViews;

    /**
     * 0: thông báo 1: tin thường, 2: chuc mung sinh nhat
     */
    private int type;

    /** user liked list */
    private List<NewsN05DTO> userLikeList;

    private int totalLike;

    /** comments */
    private List<NewsN01DTO> commentList;

    /** yyyy-mm-dd s */
    private LocalDate createDate;
}
