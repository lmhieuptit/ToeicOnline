package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.common.model.UserInfoDTO;
import com.fsoft.ez.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-user-info")
    public UserInfoDTO getUserInfo() {
        return userService.getUserInformation();
    }
}
