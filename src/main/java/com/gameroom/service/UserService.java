package com.gameroom.service;

import com.gameroom.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void login(UserDto userDto);

    void register(UserDto userDto);
}
