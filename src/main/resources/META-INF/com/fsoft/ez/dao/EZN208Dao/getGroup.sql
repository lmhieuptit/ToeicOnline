select
	g.group_id,
	g.group_name,
	g.cover_image_url,
	count( gm.group_member_id ) as total_member
from
	`group` g join group_member gm on
	g.group_id = gm.group_id
	and gm.delete_flag = 0
where
	g.delete_flag = false
	and g.group_id not in(
		select
			group_id
		from
			group_member
		where
			user_id = /* userId */'1'
			and delete_flag = false
	)
	and g.privacy = false
	and g.group_name like /* key */'key'
group by
	g.group_id
order by g.group_name COLLATE utf8_vietnamese_ci asc;