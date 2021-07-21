package com.fsoft.ez.dto;

import lombok.Data;

@Data
public class NewsN05DTO {
    public NewsN05DTO(String reactionName, Long userId) {
        super();
        this.reactionName = reactionName;
        this.userId = userId;
    }

    private String reactionName;
    private Long userId;
}
