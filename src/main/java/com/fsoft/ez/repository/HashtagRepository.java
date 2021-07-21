package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findByHashtagNameLike(String keyword);

    @Query("from Hashtag h where h.hashtagName = :name and h.deleteFlag = false")
    List<Hashtag> findByHashtagNameActive(@Param("name") String name);
    
    @Query(value = "select hashtag_name from news_hashtag nh join hashtag h on nh.hashtag_id=h.hashtag_id where nh.news_id=:newsId", nativeQuery = true)
	List<String> getListHashTag(@Param("newsId") Long newsId);
}
