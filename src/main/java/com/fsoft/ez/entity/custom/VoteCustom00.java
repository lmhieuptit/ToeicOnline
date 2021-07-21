package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class VoteCustom00 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false, insertable = false, updatable = false)
    private Long voteId;
    private String question;
    private Long voteOptionalId;
    private String optionalAnswer;
    private Long userVoteOptionId;
    private String voteUserId;
}
