package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.dto.EZN201N01DTO;
import com.fsoft.ez.entity.NewsHashtag;

public interface NewsHashtagRepository extends JpaRepository<NewsHashtag, Long> {

	List<NewsHashtag> findByNewsId(Long id);
	
	@Query("select new com.fsoft.ez.dto.EZN201N01DTO(nh.newsId, nh.hashtagId, h.hashtagName) "
			+ "from NewsHashtag nh join Hashtag h on nh.hashtagId = h.hashtagId where nh.newsId = :newsId and h.deleteFlag = false")
	List<EZN201N01DTO> getListHashtagByNewsId(@Param("newsId") Long id);
	
	@Query("select nh from NewsHashtag nh join Hashtag h on h.hashtagId = nh.hashtagId where h.hashtagName not in :nonExitsHashtagList and nh.newsId = :newsId "
			+ "and h.deleteFlag = false")
	List<NewsHashtag> getNewsHashtagNotIn(@Param("nonExitsHashtagList") List<String> notInList, @Param("newsId") Long newsId);
	
	List<NewsHashtag> deleteByNewsId(Long id);
	
	@Query("select h.hashtagName from Hashtag h join NewsHashtag nh on h.hashtagId = nh.hashtagId where nh.newsId = :newsId and h.deleteFlag = false")
	List<String> getHashtagNamesByNewsId(@Param("newsId") Long id);
}
