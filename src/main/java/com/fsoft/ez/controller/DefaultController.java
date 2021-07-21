package com.fsoft.ez.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class DefaultController implements ErrorController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/sale-login")
    public String saleLogin() {
        return "salelogin";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @Override
    public String getErrorPath() {
        return "error/403";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "resetpassword";
    }

    @GetMapping("/get-started")
    public String getStarted() {
        return "get-started";
    }

    @GetMapping("/terms-conditions-page")
    public String termsConditions() {
        return "terms-conditions";
    }

    @GetMapping("/change-pass")
    public String changepass() {
        return "change-pass";
    }

    @GetMapping("/change-pass-success")
    public String changePassSuccess() {
        return "change-pass-success";
    }

    @GetMapping("/terms-conditions")
    public String term() {
        return "terms-conditions";
    }

    @GetMapping("/intro-page")
    public String introPage() {
        return "intro-page";
    }

    @GetMapping("/password-setting")
    public String passwordSetting() {
        return "password-setting";
    }

    @GetMapping("/password-setting-completed")
    public String passwordSettingCompleted() {
        return "password-setting-completed";
    }

}
