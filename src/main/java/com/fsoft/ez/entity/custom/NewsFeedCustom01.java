package com.fsoft.ez.entity.custom;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

@Data
@Entity
public class NewsFeedCustom01 {
	
	@Column(name = "news_id")
	private Long newsId;
	
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "group_id")
	private Long groupId;
	
	@Column(name = "group_name")
	private String groupName;
	
	@Column(name = "author_info")
	private String authorInfoJsonObject;
	
	@Column(name = "department_id")
	private Long departmentId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "person_info")
	private String personInfoJsonObject;
	
	@Column(name = "json_array_hashtag")
	private String jsonArrayHashtag;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "thumbnail_url")
	private String thumbnailUrl;
	
	@Column(name = "attachment")
	private String attachment;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "question_end_date")
	private String questionEndDate;
	
	@Column(name = "number_of_choice")
	private Integer numberOfChoice;
	
	@Column(name = "json_vote_array")
	private String jsonVoteArray;
	
	@Column(name = "total_views")
	private Long totalViews;
	
	@Column(name = "users_id_liked")
	private String usersIdLiked;
	
	@Column(name = "parent_total_comment")
	private Long parentTotalComment;
	
	@Column(name = "parent_cmt_json")
	private String parentCmtJson;

	@Column(name = "child_cmt_json")
	private String childCmtJson;
	
	@Column(name = "total_cmt")
	private Long totalCmt;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "approve_date")
	private LocalDateTime approveDate;
}
