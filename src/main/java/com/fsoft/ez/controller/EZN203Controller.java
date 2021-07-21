package com.fsoft.ez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.model.response.EZN203N02Response;
import com.fsoft.ez.model.response.EZN203N03Response;
import com.fsoft.ez.service.EZN203Service;

@RestController
@RequestMapping("/api")
public class EZN203Controller {

    @Autowired
    private EZN203Service ezn203Service;

    //
    // @GetMapping("/new-notifacation")
    // public List<NewsN00DTO> getGeneralNews() {
    // return this.ezn203Service.getGeneralNews(limit, offset);
    // }

    // @PostMapping(value = "/like-news")
    // public EZN203N00Respose likeNews(@RequestBody EZN203N00Request request) {
    // EZN203N07DTO dto = modelMapper.map(request, EZN203N07DTO.class);
    // EZN203N07DTO rs = ezn203Service.likeNews(dto);
    // return modelMapper.map(rs, EZN203N00Respose.class);
    // }

    // @PostMapping(value = "/like-comment")
    // public EZN203N01Respose likeComment(@RequestBody EZN203N01Request
    // request) {
    // EZN203N08DTO dto = modelMapper.map(request, EZN203N08DTO.class);
    // EZN203N08DTO rs = ezn203Service.likeComment(dto);
    // return modelMapper.map(rs, EZN203N01Respose.class);
    // }

    // @PostMapping(value = "/comment")
    // public boolean likeComment(@RequestBody EZN203N02Request request) {
    // NewsN09DTO dto = this.modelMapper.map(request, NewsN09DTO.class);
    // return this.ezn203Service.comment(dto);
    // }
    //
    // @PostMapping(value = "/vote")
    // public boolean vote(@RequestBody EZN203N03Request request) {
    // NewsN10DTO dto = this.modelMapper.map(request, NewsN10DTO.class);
    // return this.ezn203Service.vote(dto);
    // }

    @GetMapping("/new-notifications-all")
    public List<EZN203N02Response> getNewNotifications() {

        return this.ezn203Service.getNewNotifications();
    }

    @GetMapping("/new-detail")
    public EZN203N03Response getNewsDetail(
            @RequestParam("newsId") Long newsId) {

        return this.ezn203Service.getNewsDetail(newsId);
    }
}
