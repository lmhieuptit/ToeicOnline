package com.fsoft.ez.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.model.request.EZN211N00Request;
import com.fsoft.ez.model.request.EZN211N01Request;
import com.fsoft.ez.model.request.EZN211N02Request;
import com.fsoft.ez.model.request.EZN211N03Request;
import com.fsoft.ez.model.request.EZN211N05Resquest;
import com.fsoft.ez.model.request.EZN211N07Request;
import com.fsoft.ez.model.response.EZN211N00Response;
import com.fsoft.ez.model.response.EZN211N02Response;
import com.fsoft.ez.model.response.EZN211N03Response;
import com.fsoft.ez.service.EZN211Service;

@RestController
@RequestMapping("/api")
public class EZN211Controller {

    @Autowired
    private EZN211Service ezn211Service;

    @PostMapping("/create-news-group")
    public String createNewsGroup(@Valid EZN211N00Request request)
            throws IOException {
        this.ezn211Service.createNewsGroup(request);
        return Constants.SUCCESS_MSG;
    }

    @GetMapping("/get-detail-news-group")
    public EZN211N00Response getDetailNewsGroupById(
            @RequestParam("newsId") Long id) {
        return this.ezn211Service.getDataForEditNews(id);
    }

    @PutMapping("/edit-news-group")
    public String editNewsGroup(EZN211N01Request request) throws IOException {
        this.ezn211Service.updateNewsGroup(request);
        return Constants.SUCCESS_MSG;
    }

    @GetMapping("/all-members-group")
    public List<EZN211N02Response> getAllMembers(
            @Valid EZN211N07Request request) {
        return this.ezn211Service.getAllMembers(request);

    }

    @GetMapping("/admin/get-unapproved-news")
    public List<EZN211N03Response> getNewsNeedApprove(
            EZN211N03Request request) {
        return this.ezn211Service.getNewsUnapproved(request);

    }

    @PutMapping("/admin/approve-group-news")
    public String approveNewsInGroup(@RequestBody EZN211N02Request request)
            throws Exception {
        return this.ezn211Service.approveNewsInGroup(request);
    }

    @PutMapping("/admin/reject-group-news")
    public String rejectNewsInGroup(@RequestBody EZN211N05Resquest request)
            throws Exception {
        return this.ezn211Service.rejectNewsInGroup(request);

    }

    @GetMapping("/introduce-group")
    public String getIntroduceGroup(@RequestParam("groupId") Long groupId) {

        return this.ezn211Service.getIntroduceGroup(groupId);

    }
}