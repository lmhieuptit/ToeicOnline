package com.fsoft.ez.controller;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.model.request.*;
import com.fsoft.ez.model.response.EZN210N001Response;
import com.fsoft.ez.model.response.EZN210N002Response;
import com.fsoft.ez.model.response.EZN210N003Response;
import com.fsoft.ez.model.response.EZN210N004Response;
import com.fsoft.ez.service.EZN210Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EZN210Controller {

    @Autowired
    EZN210Service ezn210Service;

    /**
     * get groupdetail
     *
     * @param EZN210N01Request request
     * @return descr of group
     */
    @GetMapping("/group-detail")
    public EZN210N001Response getGroupDetail(@Valid EZN210N01Request request) {
        return this.ezn210Service.getGroupDetail(request.getGroupId());
    }

    /**
     * get 10 newest members
     *
     * @param EZN210N002Request ezn210n002Request
     * @return list newMembers
     */
    @GetMapping("/group-new-members")
    public List<EZN210N002Response> getNewMembers(@RequestParam Long groupId) {
        return this.ezn210Service.getNewMembers(groupId);
    }

    /**
     * get Notifications
     *
     * @param EZN210N003Request request
     * @return List Notifications
     */
    @GetMapping("/group-notifications")
    public List<EZN210N003Response> getNotifications(
            @Valid EZN210N003Request request) {
        return this.ezn210Service.getNotifications(request);
    }

    @GetMapping("/suggest-members")
    public List<EZN210N004Response> getSuggestMembers(String keySearch, Long groupId) {
        return this.ezn210Service.getSuggestMembers(keySearch, groupId);
    }

    @Modifying
    @PutMapping("/set-cover-image-group")
    public String setCoverImage(@Valid EZN211N06Request request)
            throws Exception {
        this.ezn210Service.setCoverImage(request);
        return Constants.SUCCESS_MSG;

    }

    @PostMapping("/add-member-group")
    public String addMemberGroup(@Valid @RequestBody EZN210N004Request request) throws Exception {

        this.ezn210Service.addMemberGroup(request);
        return Constants.SUCCESS_MSG;
    }
}
