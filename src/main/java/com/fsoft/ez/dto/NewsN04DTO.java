package com.fsoft.ez.dto;

import lombok.Data;

/**
 * user voted class
 */
@Data
public class NewsN04DTO {

    public NewsN04DTO(String userId, String username, String avatarURL) {
        super();
        this.userId = userId;
        this.username = username;
        this.avatarURL = avatarURL;
    }

    private String userId;
    private String username;
    private String avatarURL;
}
