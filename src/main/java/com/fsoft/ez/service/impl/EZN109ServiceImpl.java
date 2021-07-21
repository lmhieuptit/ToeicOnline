package com.fsoft.ez.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.model.request.EZN109001Request;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.NotificationRepository;
import com.fsoft.ez.service.EZN109Service;

@Service
public class EZN109ServiceImpl implements EZN109Service {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NotificationRepository notificationRipository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void rejectNews(EZN109001Request ezn109n00Request) {

        News news = this.newsRepository.findById(ezn109n00Request.getNewsId())
                .get();
        news.setReasonReject(ezn109n00Request.getReasonReject());
        news.setApproveDate(LocalDateTime.now());
        news.setStatus(2);
        this.newsRepository.save(news);

        if (news.getStatus() == Constants.NEWS_STATUS_REJECT) {

            Notification notification = new Notification();

            notification.setNewsId(news.getNewsId());
            notification.setTypeNotifi(Constants.NEWS_APPROVED_OR_REJECT);

            this.notificationRipository.save(notification);
        }
    }

}
