package com.gameroom.adminpanel.controller;

import com.gameroom.adminpanel.service.AdminMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(adminMessageService.getMessages());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMessage(@RequestParam Integer id) {
        return ResponseEntity.ok(adminMessageService.deleteMessage(id));
    }
}
