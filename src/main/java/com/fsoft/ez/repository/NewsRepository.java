package com.fsoft.ez.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM news WHERE news_id =:id AND delete_flag = 0")
	News findNewsById(@Param("id") Long id);

	@Query("from News n where n.newsId = :id and n.deleteFlag = false")
	Optional<News> findNewsActiveById(@Param("id") Long id);

	@Modifying
	@Query("update News n set n.deleteFlag = true where n.newsId = :id")
	void deleteNewsLogic(@Param("id") Long id);

	@Modifying
	@Query("update News n set n.deleteFlag = true where n.newsId = :id and n.groupId = :groupId")
	void deleteNewsGroupLogic(@Param("id") Long id, @Param("groupId") Long groupId);

	@Modifying
	@Query(value = "update News n SET n.status =:status WHERE n.newsId =:newId")
	void editStatus(@Param("status") Integer status, @Param("newId") Long newId);

	@Query("select n.likeId from News n where n.newsId = :newsId and n.deleteFlag = false and n.status = 1")
	Optional<String> findUserslikeNewsActive(@Param("newsId") Long id);

	@Modifying
	@Query("update News n set n.likeId = :newUserLikedList where n.newsId = :newsId and n.deleteFlag = false and n.status = 1")
	void likeNewsActive(@Param("newUserLikedList") String list, @Param("newsId") Long id);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM news WHERE category_id =:categoryNewsId")
	Long countNewsInCategory(@Param("categoryNewsId") Long categoryNewsId);

	@Query("from News n where n.type < 2 and n.deleteFlag = false and n.draftFlag = false")
	List<News> getNewsNormalActiveList();

	@Query(nativeQuery = true, value = "SELECT * FROM news n WHERE n.delete_flag = false AND n.type < 2 AND n.author_id = :authorId")
	List<News> findNewsByIdOfUser(@Param("authorId") Long authorId);

}