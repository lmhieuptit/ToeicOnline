with sub_comment_info as(
    select
        news_id,
        first_parent_cmt_info, -- cmt cha mới nhất của bài viết
        first_child_cmt_info -- cmt con mới nhất của cmt cha này
    FROM
        (
            select
                cmt.news_id as news_id,
--                 parent_cmt.total as parent_total_cmt,
                cmt.comment_id,
                cmt.content,
                cmt.user_id,
                cmt.like_id,
                json_object(
                    'childTotalComment',
                    (select count(1) from comment where parent_comment_id = cmt.comment_id), -- Tổng số comment con của comment cha này
                    'cmtId',
                    cmt.comment_id,
                    'content',
                    cmt.content,
                    'commentBy',
                    sub_user_create_cmt.person_info,
                    'likedBy',
                    sub_user_liked_cmt.person_info,
                    'createdDate',
                    cmt.create_date
                ) as first_parent_cmt_info,
                cmt.create_date,
                sub_user_liked_cmt.person_info as user_liked_parent_cmt
            from
                comment AS cmt
                INNER JOIN (
                    SELECT
                        news_id,
                        count(1) AS total,
                        max(create_date) AS create_date
                    from
                        comment
                    WHERE
                        parent_comment_id IS NULL
                    GROUP BY
                        news_id
                ) AS parent_cmt ON cmt.news_id = parent_cmt.news_id
                AND cmt.create_date = parent_cmt.create_date
                left join (
                    select
                        comment.comment_id,
                        comment.like_id,
                        json_arrayagg(
                            json_object(
                                'userId',
                                e.emplid,
                                'account',
                                e.account,
                                'avatarUrl',
                                e.avatar_url,
                                'departmentId',
                                e.deptid,
                                'departmentDescription',
                                d.descr,
                                'departmentDescriptionShort',
                                d.descrshort
                            )
                        ) as person_info
                    from
                        tbl_employee e
                        left join tbl_department d on e.deptid = d.deptid
                        join comment on (
                            select
                                json_contains(comment.like_id, concat('"', e.emplid, '"'), '$')
                        ) = 1
                    group by
                        comment.comment_id
                ) as sub_user_liked_cmt on sub_user_liked_cmt.comment_id = cmt.comment_id
                left join (
                    select
                        e.emplid,
                        json_object(
                            'userId',
                            e.emplid,
                            'account',
                            e.account,
                            'avatarUrl',
                            e.avatar_url,
                            'departmentId',
                            e.deptid,
                            'departmentDescription',
                            d.descr,
                            'departmentDescriptionShort',
                            d.descrshort
                        ) as person_info
                    from
                        tbl_employee e
                        left join tbl_department d on e.deptid = d.deptid
                ) as sub_user_create_cmt on sub_user_create_cmt.emplid = cast(cmt.user_id as char)
        ) AS sub_cmt
        LEFT JOIN (
            SELECT
                child_cmt.total as child_total_cmt,
                cmt2.comment_id as child_comment_id,
                cmt2.content as child_comment_content,
                cmt2.user_id,
                sub_user_create_child_cmt.person_info as create_child_cmt_by,
                cmt2.like_id,
                sub_user_liked_child_cmt.person_info as user_liked_child_cmt,
                child_cmt.parent_comment_id,
                child_cmt.create_date,
                json_object(
                    'cmtId',
                    cmt2.comment_id,
                    'content',
                    cmt2.content,
                    'commentBy',
                    sub_user_create_child_cmt.person_info,
                    'likedBy',
                    sub_user_liked_child_cmt.person_info,
                    'createdDate',
                    child_cmt.create_date
                ) as first_child_cmt_info
            FROM
                comment AS cmt2
                left JOIN (
                    SELECT
                        news_id,
                        parent_comment_id,
                        count(1) AS total,
                        max(create_date) as create_date
                    from
                        comment
                    WHERE
                        parent_comment_id IS NOT NULL
                    GROUP BY
                        news_id,
                        parent_comment_id
                ) AS child_cmt ON cmt2.news_id = child_cmt.news_id
                AND cmt2.parent_comment_id = child_cmt.parent_comment_id
                AND cmt2.create_date = child_cmt.create_date
                left join (
                    select
                        comment.comment_id,
                        comment.like_id,
                        json_arrayagg(
                            json_object(
                                'userId',
                                e.emplid,
                                'account',
                                e.account,
                                'avatarUrl',
                                e.avatar_url,
                                'departmentId',
                                e.deptid,
                                'departmentDescription',
                                d.descr,
                                'departmentDescriptionShort',
                                d.descrshort
                            )
                        ) as person_info
                    from
                        tbl_employee e
                        left join tbl_department d on e.deptid = d.deptid
                        join comment on (
                            select
                                json_contains(comment.like_id, concat('"', e.emplid, '"'), '$')
                        ) = 1
                    group by
                        comment.comment_id
                ) as sub_user_liked_child_cmt on sub_user_liked_child_cmt.comment_id = cmt2.comment_id
                left join (
                    select
                        e.emplid,
                        json_object(
                            'userId',
                            e.emplid,
                            'account',
                            e.account,
                            'avatarUrl',
                            e.avatar_url,
                            'departmentId',
                            e.deptid,
                            'departmentDescription',
                            d.descr,
                            'departmentDescriptionShort',
                            d.descrshort
                        ) as person_info
                    from
                        tbl_employee e
                        left join tbl_department d on e.deptid = d.deptid
                ) as sub_user_create_child_cmt on sub_user_create_child_cmt.emplid = cast(cmt2.user_id as char)
        ) AS child_cmt ON child_cmt.parent_comment_id = sub_cmt.comment_id
)
select
    n.news_id,
    n.category_id,
    c.category_name,
    n.group_id,
    g.group_name,
    sub_emp.author_info,
    n.department_id,
    sub_person.person_info,
    sub_hashtag.json_array_hashtag,
    n.title,
    n.content,
    n.thumbnail_url,
    n.attachment,
    n.`type`,
    n.question,
    DATE_FORMAT(n.question_end_date, '%d/%m/%Y') as question_end_date,
    n.number_of_choice,
    sub_vote.json_vote_array,
    n.total_views,
    case
        when json_arrayagg(sub_user_liked.person_info) = json_array(null) then null
        else json_arrayagg(sub_user_liked.person_info)
    end as users_id_liked,
    (SELECT
                        count(1)
                    from
                        comment
                    WHERE
                        parent_comment_id IS null and news_id = n.news_id) as parent_total_comment,
    (
        select
            first_parent_cmt_info
        from
            sub_comment_info
        where
            news_id = n.news_id
    ) as parent_cmt_json,
    (
        select
            first_child_cmt_info
        from
            sub_comment_info
        where
            news_id = n.news_id
    ) as child_cmt_json,
    (
        select
            count(1)
        from
            comment
        where
            comment.news_id = n.news_id
    ) as total_cmt,
    (
        select
            descr
        from
            tbl_department
        where
            parent_dept is null
            or parent_dept = ''
    ) as company_name,
    n.create_date,
    n.approve_date
from
    news n
    left join category c on n.category_id = c.category_id
    left join `group` g on g.group_id = n.group_id
    left join (
        select
            nh.news_id as news_id,
            json_arrayagg(h.hashtag_name) as json_array_hashtag
        from
            hashtag h
            join news_hashtag nh on h.hashtag_id = nh.hashtag_id
        where
            h.delete_flag = 0
        group by
            news_id
    ) as sub_hashtag on n.news_id = sub_hashtag.news_id
    left join (
        select
            e.emplid,
            case
                when e.emplid is null then null
                else json_object(
                    'userId',
                    e.emplid,
                    'account',
                    e.account,
                    'avatarUrl',
                    e.avatar_url,
                    'departmentId',
                    e.deptid,
                    'departmentDescription',
                    d.descr,
                    'departmentDescriptionShort',
                    d.descrshort
                )
            end as person_info
        from
            tbl_employee e
            left join tbl_department d on e.deptid = d.deptid
    ) as sub_user_liked on (
        select
            json_contains(
                n.like_id,
                concat('"', sub_user_liked.emplid, '"'),
                '$'
            )
    ) = 1
    left join (
        select
            vo.news_id as news_id,
            JSON_ARRAYAGG(
                JSON_OBJECT(
                    'id',
                    vo.vote_option_id,
                    'voteContent',
                    vo.option_answer,
                    'userChoiceList',
                    sub_user_voted.person_info
                )
            ) as json_vote_array
        FROM
            vote_option vo
            left join (
                select
                    e.emplid,
                    vote_option.vote_option_id,
                    vote_option.vote_user_id,
                    json_arrayagg(
                        json_object(
                            'userId',
                            e.emplid,
                            'account',
                            e.account,
                            'avatarUrl',
                            e.avatar_url,
                            'departmentId',
                            e.deptid,
                            'departmentDescription',
                            d.descr,
                            'departmentDescriptionShort',
                            d.descrshort
                        )
                    ) as person_info
                from
                    tbl_employee e
                    left join tbl_department d on e.deptid = d.deptid
                    join vote_option on (
                        select
                            json_contains(
                                vote_option.vote_user_id,
                                concat('"', e.emplid, '"'),
                                '$'
                            )
                    ) = 1
                group by
                    vote_option.vote_option_id
            ) as sub_user_voted on sub_user_voted.vote_option_id = vo.vote_option_id
        where
            vo.delete_flag = 0
        group by
            news_id
    ) as sub_vote on sub_vote.news_id = n.news_id
    left join (
        select
            e.emplid,
            case
                when e.emplid is null then null
                else json_object(
                    'authorId',
                    e.emplid,
                    'authorAccount',
                    e.account,
                    'avatarUrl',
                    e.avatar_url,
                    'departmentId',
                    e.deptid,
                    'departmentDescription',
                    d.descr,
                    'departmentDescriptionShort',
                    d.descrshort
                )
            end as author_info
        from
            tbl_employee e
            left join tbl_department d on e.deptid = d.deptid
    ) as sub_emp on sub_emp.emplid = cast(n.author_id as char)
    left join (
        select
            e.emplid,
            case
                when e.emplid is null then null
                else json_object(
                    'personId',
                    e.emplid,
                    'personAccount',
                    e.account,
                    'avatarUrl',
                    e.avatar_url,
                    'departmentId',
                    e.deptid,
                    'departmentDescription',
                    d.descr,
                    'departmentDescriptionShort',
                    d.descrshort
                )
            end as person_info
        from
            tbl_employee e
            left join tbl_department d on e.deptid = d.deptid
    ) as sub_person on sub_person.emplid = cast(n.personal_id as char)
where
    n.delete_flag = 0
    and n.status = 1
    and n.draft_flag = 0
    and (
        select
            deptid
        from
            tbl_department
        where
            parent_dept is null
            or parent_dept = ''
    ) = /* companyId */'6600000000'
    and (
        n.personal_id = /* personId */3
        or n.personal_id is null
    )
    and (
        n.group_id in (
            select
                gm.group_id
            from
                group_member gm
            where
                gm.user_id = /* personId */3
        )
        or n.group_id is null
    )
    and (
        case
            when n.`type` = 2 then (
                select
                    n.author_id = /* personId */3
            ) = 1
            else true
        end
    ) = true
    and (
        n.title like/* title */'%'
        OR (
            SELECT
                count(hashtag)
            FROM
                JSON_TABLE(
                    sub_hashtag.json_array_hashtag,
                    "$[*]" COLUMNS(
                        hashtag varchar(50) PATH "$"
                    )
                ) sub_hashtag_row
            where
                hashtag like /* hashtag */'%'
        ) > 0
    )
    /*%if categoryIsEmpty == false */
    and (
        n.category_id in /* categoryIdList */(1, 2, 3)
        or n.category_id is null
    )
	/*%end*/
group by
    news_id
    /*# orderByClause */
    /*# limitClause */