package com.fsoft.ez.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.VoteOption;
import com.fsoft.ez.model.response.EZN201N03Response;

public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {

	List<VoteOption> findByNewsId(Long newId);
	
	List<VoteOption> findByDeleteFlagAndNewsIdIn(boolean deleteFlag, List<Long> newIds);

	List<VoteOption> findByNewsIdIn(List<Long> newIds);
	
	@Modifying
	@Query("update VoteOption tv set tv.deleteFlag = false where tv.voteOptionId not in :voteOptionIdList and tv.newsId = :newsId")
	void updateVoteOptionByIdNotIn(@Param("voteOptionIdList")List<Long> voteOptionIdList, @Param("newsId") Long voteId);
	
	@Query("from VoteOption tv where tv.voteOptionId not in :voteOptionIdList and tv.newsId = :newsId")
	List<VoteOption> findByVoteIdNotIn(@Param("voteOptionIdList") List<Long> voteOptionIdList,
			@Param("newsId") Long voteId);
	
	@Query("select new com.fsoft.ez.model.response.EZN201N03Response(vo.voteOptionId, vo.optionAnswer) from VoteOption vo where vo.newsId = :newsId and vo.deleteFlag = false")
	List<EZN201N03Response> getvoteOptionListActive(@Param("newsId") Long id);
	
	@Query("select vo.voteUserId from VoteOption vo where vo.voteOptionId = :voteId and vo.deleteFlag = false")
	Optional<String> getUserVotedJsonArr(@Param("voteId") Long voteId);
	
	@Modifying
	@Query("update VoteOption vo set vo.voteUserId = :newUserVotedJsonArr where vo.voteOptionId = :voteId")
	void voteActionForVoteOption(@Param("newUserVotedJsonArr") String newUserVotedJsonArr, @Param("voteId") Long voteId);
	
	@Query(value = "select option_answer from vote_option v where v.news_id = :newsId", nativeQuery = true)
	List<String> getListVoteOption(@Param("newsId") Long newsId);
}
