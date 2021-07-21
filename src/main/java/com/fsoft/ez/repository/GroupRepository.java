package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByGroupId(Long id);

    @Query(nativeQuery = true, value = " SELECT * FROM `group`  WHERE deletE_flag = 0 AND group_id =:groupId ")
    Group getGroupById(@Param("groupId") Long groupId);

    @Query(value = "SELECT gr.description FROM Group gr WHERE  gr.deleteFlag = 0 AND gr.groupId =:groupId ")
    String getIntroduceGroup(@Param("groupId") Long groupId);
    
    @Modifying
    @Query(nativeQuery = true , value = " UPDATE `group` SET cover_image_url =:filePath WHERE group_id =:groupId AND delete_flag = false")
    void updateCoverImageById(@Param("filePath") String filePath, @Param("groupId") Long groupId );
}
