/**
 * Copyright © 2020 Suntory Corporation. All Rights Reserved.
 **/
package com.fsoft.ez.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "notification")
public class Notification implements Serializable {
	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id", nullable = false)
	private Long notificationId;

	@Column(name = "company_id", nullable = true)
	private Long copanyId;

	@Column(name = "group_id", nullable = true)
	private Long groupId;

	@Column(name = "to_user", nullable = true, columnDefinition = "json")
	private String toUser;

	@Column(name = "is_seen", nullable = true, columnDefinition = "json")
	private String isSeen;

	@Column(name = "news_id", nullable = true)
	private Long newsId;

	@Column(name = "process_id", nullable = false)
	private Long processId;

	@Column(name = "create_date", nullable = true)
	private LocalDateTime createDate;

	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;

	@Column(name = "type_notification ", nullable = true)
	private Integer typeNotifi;

	@Column(name = "member_id", nullable = true)
	private String memberId;

	@PrePersist
	protected void onCreate() {
		this.createDate = this.updateDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateDate = LocalDateTime.now();
	}
}