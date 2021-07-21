package com.fsoft.ez.model.response;

import java.util.List;

import javax.persistence.Column;

import com.fsoft.ez.entity.custom.EZN210N01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EZN210N001Response {

    private String groupName;

    private String description;
    
    private Long countMembers;
    
    /**
     * 0: group mở, 1: group kín
     */
    private Integer privacy ;
    
    private String coverImageUrl;
    
    private Integer roleGroup ;
    
    private List<EZN210N01> memberGroupList;
    
    private List<EZN210N01> adminGroupList;

}