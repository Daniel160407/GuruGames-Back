package com.gameroom.adminpanel.service;

import com.gameroom.adminpanel.model.Admin;
import com.gameroom.dto.UserDto;
import com.gameroom.service.exception.adminpanel.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthorizationServiceImpl implements AdminAuthorizationService {
    @Value("${ADMIN_EMAIL}")
    private String adminEmail;
    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Override
    public void login(Admin admin) {
        if (!(admin.getEmail().equals(adminEmail) && admin.getPassword().equals(adminPassword))) {
            throw new InvalidEmailOrPasswordException("Invalid email or password!");
        }
    }
}
