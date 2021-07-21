package com.fsoft.ez.entity.custom;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectSiteProgress {
    private String id;
    @Id
    private Long siteId;
    private Date createdAt;
    private String projectTitle;
    private String displayId;
    private int countSite;
    private String salesLocationId;
    private Long supervisor;
    private Long designer;

}
