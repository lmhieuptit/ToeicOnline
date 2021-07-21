package com.fsoft.ez.entity.custom;

import lombok.Data;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Data
@Entity
public class NewsFeedCustom02 {

    @Column(name = "cmt_json")
    private String cmtJson;
    
}
