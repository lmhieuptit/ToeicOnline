package com.fsoft.ez.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsN01DTO implements Comparable<NewsN01DTO> {
    private Long commentId;
    private Long parentCommentId;
    private String userId;
    private String userName;
    private String avatarURL;
    private String comment;
    private LocalDateTime createDate;
    private Set<NewsN01DTO> CommentList;
    private List<NewsN06DTO> reactionList;

    @Override
    public int compareTo(NewsN01DTO o) {
        if (createDate == null && o.getCreateDate() == null)
            return 1;
        if (createDate == null && o.getCreateDate() != null)
            return -1;
        if (this.createDate.compareTo(o.getCreateDate()) == 0)
            return 1;
        return this.createDate.compareTo(o.getCreateDate());
    }
}
