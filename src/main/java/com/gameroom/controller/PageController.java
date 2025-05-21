package com.gameroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PageController {

    @GetMapping
    public String getMainPage() {
        return "index.html";
    }

    @GetMapping(value = "/about")
    public String getAboutPage() {
        return "index.html";
    }

    @GetMapping(value = "/contact")
    public String getContactPage() {
        return "index.html";
    }

    @GetMapping(value = "/location")
    public String getLocationPage() {
        return "index.html";
    }

    @GetMapping(value = "/booking")
    public String getBookingPage() {
        return "index.html";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "index.html";
    }

    @GetMapping(value = "/register")
    public String getRegisterPage() {
        return "index.html";
    }
}
