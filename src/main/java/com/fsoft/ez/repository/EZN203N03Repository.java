package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.custom.EZN203N03;

public interface EZN203N03Repository extends JpaRepository<EZN203N03, String> {

    @Query(nativeQuery = true, value = "select g.group_name , n.type, n.author_id , d.descrshort, te.account, n.title ,n.content , h.hashtag_id , JSON_LENGTH(n.like_id) as like_count ,n.attachment ,n.question, n.thumbnail_url, n.total_views ,(select te.account from news n join tbl_employee te on  n.personal_id = te.emplid where n.news_id=:newsId) as personal_name\r\n"
            + "from news n left join `group` g on n.group_id = g.group_id\r\n"
            + "            left join tbl_employee te on n.author_id = te.emplid\r\n"
            + "            left join news_hashtag nh on n.news_id = nh.news_id\r\n"
            + "            left join hashtag h on nh.hashtag_id = h.hashtag_id\r\n"
            + "            left join vote_option v on n.news_id = v.news_id\r\n"
            + "            left join tbl_department d on d.deptid = te.deptid\r\n"
            + "where n.news_id = :newsId")
    EZN203N03 getNewsDetail(@Param("newsId") Long newsId);

}
