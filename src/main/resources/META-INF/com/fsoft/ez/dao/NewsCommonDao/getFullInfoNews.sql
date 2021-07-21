-- DucLA12
-- full info cho bài viết
select
    n.news_id,
    n.category_id,
    c.category_name,
    n.group_id,
    g.group_name,
    n.author_id,
    sub_emp.account as author_account,
    sub_emp.descrshort as descrshort,
    n.department_id,
    n.personal_id,
    sub_hashtag.json_array_hashtag,
    n.title,
    n.content,
    n.thumbnail_url,
    n.`type`,
    n.question,
    DATE_FORMAT(n.question_end_date, '%d/%m/%Y') as question_end_date,
    n.number_of_choice,
    sub_vote.json_vote_array,
    n.total_views,
    n.like_id as users_id_liked,
    sub_cmt.cmt_json_array as cmt_json_array,
    n.create_date,
    n.approve_date
from
    news n
    left join category c on n.category_id = c.category_id
    left join `group` g on g.group_id = n.group_id
    left join (
        select
            nh.news_id as news_id,
            concat(
                '[',
                group_concat(coalesce(concat('"',h.hashtag_name,'"') ,'null')),
                ']'
            ) as json_array_hashtag
        from
            hashtag h
            join news_hashtag nh on h.hashtag_id = nh.hashtag_id
        group by
            news_id
    ) as sub_hashtag on n.news_id = sub_hashtag.news_id
    left join (
        select
            vo.news_id as news_id,
            CONCAT(
                '[',
                GROUP_CONCAT(
                    CONCAT(
                        '{"id":"',
                        coalesce(concat('"',vo.vote_option_id,'"') ,'null'),
                        ',"voteContent":',
                        coalesce(concat('"',vo.option_answer,'"') ,'null'),
                        ',"userChoiceList":',
                        coalesce(concat('"',vo.vote_user_id,'"') ,'[]'),
                        '}'
                    )
                ),
                ']'
            ) as json_vote_array
        FROM
            vote_option vo
        group by
            news_id
    ) as sub_vote on sub_vote.news_id = n.news_id
    left join (
        select
            e.emplid as emplid,
            e.account as account,
            d.descrshort as descrshort
        from
            tbl_employee e
            left join tbl_department d on e.deptid = d.deptid
    ) as sub_emp on sub_emp.emplid > cast(n.author_id as char)
    left join (
    select 
    	cmt.news_id as news_id,
    	CONCAT(
                '[',
                GROUP_CONCAT(
                    CONCAT(
                        '{"cmtId":',
                        coalesce(concat('"', cmt.comment_id, '"'), 'null'),
                        ',"cmtContent":',
                        coalesce(concat('"', cmt.content, '"'), 'null'),
                        ',"commentBy":',
                        coalesce(concat('"', cmt.user_id , '"'), 'null'),
                        ',"isParentCmt":',
                        coalesce(concat('"', cmt.is_parent_comment, '"'), 'null'),
                        ',"parentCmtId":',
                        coalesce(concat('"', cmt.comment_id, '"'), 'null'),
                        ',"userIdLikedList":',
                        coalesce(concat('"', cmt.like_id, '"'), '[]'),
                        '}'
                    )
                ),
                ']'
            ) as cmt_json_array
    from comment cmt
    group by cmt.news_id
    ) as sub_cmt on sub_cmt.news_id = n.news_id
where n.delete_flag = 0 and n.status = 1 and n.draft_flag = 1
group by n.news_id
/*# orderByClause */
/*# limitClause */