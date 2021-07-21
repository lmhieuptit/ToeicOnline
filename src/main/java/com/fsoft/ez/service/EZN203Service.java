package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.dto.NewsN00DTO;
import com.fsoft.ez.dto.NewsN09DTO;
import com.fsoft.ez.dto.NewsN10DTO;
import com.fsoft.ez.model.response.EZN203N02Response;
import com.fsoft.ez.model.response.EZN203N03Response;

public interface EZN203Service {

    public List<NewsN00DTO> getGeneralNews(int limit, int offset);

    // public EZN203N07DTO likeNews(EZN203N07DTO dto);

    // public EZN203N08DTO likeComment(EZN203N08DTO obj);

    public boolean comment(NewsN09DTO obj);

    public boolean vote(NewsN10DTO obj);

    List<EZN203N02Response> getNewNotifications();

    EZN203N03Response getNewsDetail(Long newsId);
}
