package com.fsoft.ez.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query(nativeQuery = true, value = "select * from `comment` where comment_id = :commentId and user_id = :userId")
	List<Comment> findByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);

	@Modifying
	@Query(nativeQuery = true, value = "delete from `comment` where comment_id = :commentId and user_id = :userId")
	void deleteByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);

	@Query(nativeQuery = true, value = "select * from `comment` where news_id = :newsId limit :limit offset :offset")
	List<Comment> getCommentNews(@Param("newsId") Long newsId, @Param("limit") Long limit,
			@Param("offset") Long offset);

	Optional<Comment> findByCommentIdAndDeleteFlag(Long commentId, boolean deleteFlag);
	
	@Query("select c.likeId from Comment c where c.commentId = :cmtId and c.deleteFlag = false")
	Optional<String> findUserslikeCommentActive(@Param("cmtId") Long cmtId);
	
	@Modifying
	@Query("update Comment c set c.likeId = :newUserLikedCommentList where c.commentId = :cmtId and c.newsId = :newsId and c.deleteFlag = false")
	void likeComment(@Param("newUserLikedCommentList") String list, @Param("cmtId") Long cmtId, @Param("newsId") Long newsId);
	
	@Modifying
	@Query("update Comment c set c.deleteFlag = true where c.commentId = :cmtId")
	void deleteCommentLogic(@Param("cmtId") Long cmtId);
	
	@Modifying
	@Query("update Comment c set c.content = :content where c.commentId = :id")
	void updateContentComment(@Param("content") String content, @Param("id") Long id);
}
