package com.gameroom.adminpanel.controller;

import com.gameroom.adminpanel.service.AdminAuthorizationService;
import com.gameroom.dto.UserDto;
import com.gameroom.service.exception.adminpanel.InvalidEmailOrPasswordException;
import com.gameroom.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adminpanel/authorization")
@CrossOrigin(origins = "*", exposedHeaders = "Authorization")
public class AdminAuthorizationController {
    private final AdminAuthorizationService adminAuthorizationService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AdminAuthorizationController(AdminAuthorizationService adminAuthorizationService, JwtUtils jwtUtils) {
        this.adminAuthorizationService = adminAuthorizationService;
        this.jwtUtils = jwtUtils;
    }

    @PutMapping
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        try {
            adminAuthorizationService.login(userDto);

            String token = jwtUtils.generateToken(userDto.getEmail(), "ADMIN");

            response.addHeader(jwtUtils.JWT_HEADER, jwtUtils.JWT_PREFIX + token);

            return ResponseEntity.accepted().build();
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
