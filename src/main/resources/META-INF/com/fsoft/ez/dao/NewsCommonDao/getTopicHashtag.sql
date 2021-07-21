select
    h.hashtag_name
from
    hashtag h
    join news_hashtag nh on h.hashtag_id = nh.hashtag_id
group by
    hashtag_name
order by
    count(h.hashtag_name) desc
limit
    10