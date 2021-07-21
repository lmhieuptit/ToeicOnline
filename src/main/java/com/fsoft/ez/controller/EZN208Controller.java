package com.fsoft.ez.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.model.response.EZN208001Response;
import com.fsoft.ez.service.EZN208Service;

@RestController
@RequestMapping("/api")
public class EZN208Controller {

    @Autowired
    private EZN208Service ezn208Service;

    @GetMapping("/list-group")
    public List<EZN208001Response> getGroup(
            @RequestParam("keySearch") String keySearch) throws IOException {
        return this.ezn208Service.getGroup(keySearch);
    }

    @GetMapping("/my-group")
    public List<EZN208001Response> getMyGroup() throws IOException {
        return this.ezn208Service.getMyGroup();
    }

    @PostMapping("/join-group")
    public ResponseEntity<String> joinGroup(
            @RequestParam("groupId") Long groupId) throws IOException {
        this.ezn208Service.joinGroup(groupId);
        return new ResponseEntity<>(Constants.SUCCESS_MSG, HttpStatus.OK);
    }

    @PostMapping("/leave-group")
    public ResponseEntity<String> leaveGroup(Long groupId) throws IOException {
        this.ezn208Service.leaveGroup(groupId);
        return new ResponseEntity<>(Constants.SUCCESS_MSG, HttpStatus.OK);

    }

    @PutMapping("/delete-group")
    public String deleteGroup(Long groupId) throws IOException {
        this.ezn208Service.deleteGroup(groupId);
        return Constants.SUCCESS_MSG;

    }
}
