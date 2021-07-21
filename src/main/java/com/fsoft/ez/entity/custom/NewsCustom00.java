package com.fsoft.ez.entity.custom;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class NewsCustom00 implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2206111137668005752L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", nullable = false, insertable = false, updatable = false)
    private Long newsId;

    private String title;
    private String content;
    private String thumbnailUrl;
    private String createUser;
    private LocalDate createDate;
    private Long totalViews;
    private String question;
    private String voteEndDate;
    private Long personalId;
    private Long categoryId;
    private String categoryName;
    private Long groupId;
    private String groupName;

    public NewsCustom00() {
    }
}
