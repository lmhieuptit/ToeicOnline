package com.fsoft.ez.entity.custom;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

@Entity
@Data
public class EZN208001 {
    @Column(name = "group_id")
    private String groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "cover_image_url")
    private String coverImageUrl;
    
    @Column(name="role_group")
    private int roleGroup;

    @Column(name = "total_member")
    private int totalMember;
}
