package com.fsoft.ez.model.response;

import lombok.Data;

/**
 * group list
 *
 * @author DanNT3
 *
 */
@Data
public class EZN208001Response {

    private Long groupId;

    private String groupName;

    private int totalMember;
    
    private int roleGroup;

    private String coverImageUrl;
}
