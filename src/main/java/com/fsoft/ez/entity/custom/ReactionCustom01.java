package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ReactionCustom01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id", nullable = false, insertable = false, updatable = false)
    private Long reactionId;
    private String reactionName;
    private Long commentId;
    private Long userId;
}
