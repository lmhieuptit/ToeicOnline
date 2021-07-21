package com.fsoft.ez.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.repository.GroupMemberRepository;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.NotificationRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.service.EZN108Service;
import com.fsoft.ez.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EZN108ServiceImpl implements EZN108Service {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private TblEmployeeRepository tblEmployeeRepository;

	@Autowired
	private GroupMemberRepository groupMemberRepository;

	@Autowired
	private NotificationRepository notificationRipository;

	@Autowired
	private UserService userService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void acceptNews(Long newsId) {

		String emplId = this.userService.getUserInformation().getEmplId();

		News news = this.newsRepository.findById(newsId).get();
		news.setApproverId(emplId);
		news.setStatus(Constants.NEWS_STATUS_APPROVED);
		news.setApproveDate(LocalDateTime.now());
		news.setApproverId(
				((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmplid());
		this.newsRepository.save(news);

		// approve news
		if (news.getStatus() == Constants.NEWS_STATUS_APPROVED) {
			Notification notification = new Notification();

			notification.setNewsId(newsId);
			notification.setTypeNotifi(Constants.NEWS_APPROVED_OR_REJECT);

			this.notificationRipository.save(notification);
		}

		if (news.getType() == 1 && news.getStatus() == Constants.NEWS_STATUS_APPROVED) {
			// Persional Wall
			// if (news.getPersonalId() != null) {
			//
			// }

			// group wall
			if (news.getGroupId() != null && news.getStatus() == Constants.NEWS_STATUS_APPROVED) {

				Notification notification = new Notification();
				List<Long> memberList = this.groupMemberRepository.getUserIdByGroupId(news.getGroupId());

				String json = null;
				ObjectMapper objectMapper = new ObjectMapper();
				try {
					json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(memberList);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				// - Bài viết trên nhóm được phê duyệt
				notification.setNewsId(news.getNewsId());
				notification.setGroupId(news.getGroupId());
				notification.setTypeNotifi(Constants.NEWS_GROUP_APPROVED);
				notification.setToUser(json);

				this.notificationRipository.save(notification);

				// - Trong nhóm có bài viết mới
				Notification notifi = new Notification();
				notifi.setTypeNotifi(Constants.NEW_NEWS_GROUP);
				this.notificationRipository.save(notifi);

			}
			// companyWall

			if (news.getDepartmentId() != null && news.getStatus() == Constants.NEWS_STATUS_APPROVED) {

				Notification notification = new Notification();

				List<String> userIdList = this.tblEmployeeRepository
						.getUserIdByDeptId(news.getDepartmentId().toString());

				String json = null;
				ObjectMapper objectMapper = new ObjectMapper();
				try {
					json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userIdList);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				notification.setNewsId(news.getNewsId());
				notification.setCopanyId(news.getDepartmentId());
				notification.setTypeNotifi(Constants.NEW_NEWS_COMPANY);
				notification.setToUser(json);

				this.notificationRipository.save(notification);
			}

		}
	}

}
