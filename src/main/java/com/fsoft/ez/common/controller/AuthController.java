package com.fsoft.ez.common.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.common.model.AuthRequest;
import com.fsoft.ez.common.model.AuthResponse;
import com.fsoft.ez.common.utils.JwtTokenUtil;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class AuthController {

    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    public AuthController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        TblEmployee empl = this.userService.getUserInfo(request.getUsername());
        if (empl == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwttoken(this.jwtTokenUtil.generateToken(empl.getLowerEmailAddr()));

        return ResponseEntity.ok().body(authResponse);
    }
}
