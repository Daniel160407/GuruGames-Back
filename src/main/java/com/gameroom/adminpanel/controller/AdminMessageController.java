package com.gameroom.adminpanel.controller;

import com.gameroom.adminpanel.service.AdminMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpanel/contact")
@CrossOrigin(origins = "*")
public class AdminMessageController {
    private final AdminMessageService adminMessageService;

    @Autowired
    public AdminMessageController(AdminMessageService adminMessageService) {
        this.adminMessageService = adminMessageService;
    }

    @GetMapping
    public ResponseEntity<?> getMessages() {
        return ResponseEntity.ok().body(adminMessageService.getMessages());
    }
}
