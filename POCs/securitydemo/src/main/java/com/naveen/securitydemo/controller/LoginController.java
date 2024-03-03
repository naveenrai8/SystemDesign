package com.naveen.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("showMyLoginPage")
    public String login(){
        return "fancy-login";
    }

    // add request for /access-denied
    @GetMapping("access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
