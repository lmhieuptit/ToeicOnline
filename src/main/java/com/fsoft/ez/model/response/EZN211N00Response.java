package com.fsoft.ez.model.response;

import java.util.List;

import lombok.Data;

@Data
public class EZN211N00Response {

	private Long newsId;

    private Long categoryId;

    private String title;

    private String content;

    private String thumbnailFileName;
    
    private String thumbnailFilePath;
    
    private String fileContentJsonArr;

    private List<String> hashtagList;

    private int notificationFlag = 0;

    private EZN201N02Response vote;

}
