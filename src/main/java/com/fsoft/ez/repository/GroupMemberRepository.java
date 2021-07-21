package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.GroupMember;

@Repository
public interface GroupMemberRepository
extends JpaRepository<GroupMember, Long> {

	@Modifying
	@Query(nativeQuery = true, value = "update group_member set delete_flag = 1 where group_id = :groupId")
	void deleteByGroupId(@Param("groupId") Long groupId);

	@Modifying
	@Query(nativeQuery = true, value = "update group_member set delete_flag = 1 where group_id = :groupId and user_id in :deleteId")
	void deleteByUserId(@Param("groupId") Long groupId, @Param("deleteId") List<Long> deleteId);

	@Modifying
	@Query(nativeQuery = true, value = "update group_member set delete_flag = 0 where group_id = :groupId and user_id = :userId")
	void activeMemberByGroupIdAndUserId(@Param("groupId") Long groupId,
			@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "select group_member_id from group_member where group_id = :groupId and user_id = :userId")
	List<Long> selectByGroupIdAndUserId(@Param("groupId") Long groupId,
			@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "select * from group_member where group_id = :groupId and user_id = :userId AND delete_flag = false")
	List<GroupMember> findByGroupIdAndUserId(@Param("groupId") Long groupId,
			@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "SELECT * FROM group_member where delete_flag = 0 and group_id =:groupId  ORDER BY create_date DESC LIMIT 5")
	List<GroupMember> findNewMembers(@Param("groupId") Long groupId);

	@Query(nativeQuery = true, value = "SELECT * FROM group_member mem WHERE mem.group_id =:groupId AND mem.delete_flag = 0")
	List<GroupMember> getAllMembers(@Param("groupId") Long groupId);

	@Query(nativeQuery = true, value = "SELECT COUNT(group_member_id) FROM group_member gm INNER JOIN tbl_employee e ON gm.user_id = e.emplid  WHERE group_id =:groupId AND delete_flag = 0 ")
	Long getAmountNumbers(@Param("groupId") Long groupId);

	@Query(nativeQuery = true, value = "select count(group_member_id) FROM group_member where group_id =:groupId  and user_id =:userId")
	int countMemberGroup(@Param("groupId") Long groupId,
			@Param("userId") Long userId);

	@Query(nativeQuery = true, value = " SELECT distinct group_id FROM group_member where user_id =:emplId")
	List<Long> getGroupId(@Param("emplId") String emplId);

	@Query(value = "SELECT gm.userId FROM GroupMember gm WHERE gm.deleteFlag = false AND gm.groupId=:groupId")
	List<Long> getUserIdByGroupId(@Param("groupId") Long groupId);

	@Query(nativeQuery = true, value = "SELECT gm.role_group FROM group_member gm WHERE gm.delete_flag = false AND gm.user_id =:emplId and group_id =:groupId ")
	Integer getRoleGroup(@Param("emplId") String emplId,
			@Param("groupId") Long groupId);

	@Query(nativeQuery = true, value = "SELECT * FROM group_member gm WHERE gm.delete_flag = false AND group_id =:groupId ")
	GroupMember getGroupMemberById(@Param("groupId") Long groupId);

	@Query(nativeQuery = true, value = "SELECT * FROM group_member gm  where gm.group_id =:groupId AND gm.delete_flag = 0")
	List<GroupMember> getGroupMemberByGroupId(@Param("groupId") Long groupId);
}
