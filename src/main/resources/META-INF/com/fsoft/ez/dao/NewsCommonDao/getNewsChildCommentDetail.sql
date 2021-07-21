SELECT
    json_object(
        'newsId',
        cmt.news_id,
        'cmtId',
        cmt.comment_id,
        'content',
        cmt.content,
        'commentBy',
        sub_user_cmt.person_info,
        'parentCmtId',
        cmt.parent_comment_id,
        'likedBy',
        sub_user_liked_cmt.person_info,
        'createdDate',
        cmt.create_date
    ) as cmt_json
from
    comment AS cmt
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
    ) as sub_user_cmt on sub_user_cmt.emplid = cast(cmt.user_id as char)
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
where
    cmt.delete_flag = 0
    AND cmt.parent_comment_id IS NOT NULL
    AND cmt.parent_comment_id = /*parentId */74
GROUP BY
    cmt.news_id,
    cmt.comment_id
    /*# orderByClause */
    /*# limitClause */