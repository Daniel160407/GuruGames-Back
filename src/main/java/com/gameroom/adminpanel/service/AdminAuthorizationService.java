package com.gameroom.adminpanel.service;

import com.gameroom.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminAuthorizationService {
    void login(UserDto userDto);
}
