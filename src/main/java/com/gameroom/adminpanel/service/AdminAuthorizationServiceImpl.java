package com.gameroom.adminpanel.service;

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
    public void login(UserDto userDto) {
        if (!(userDto.getEmail().equals(adminEmail) && userDto.getPassword().equals(adminPassword))) {
            throw new InvalidEmailOrPasswordException("Invalid email or password!");
        }
    }
}
